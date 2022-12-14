package com.freshworks.SpringProject.Instances;

import com.freshworks.SpringProject.Instances.InstanceHistory.InstanceHistory;
import com.freshworks.SpringProject.Instances.InstanceHistory.InstanceHistoryJPA;
import com.freshworks.SpringProject.Instances.exceptions.*;
import com.freshworks.SpringProject.team.Team;
import com.freshworks.SpringProject.team.TeamJPA;
import com.freshworks.SpringProject.team.exceptions.TeamNotFoundException;
import com.freshworks.SpringProject.teamMembers.TeamMembers;
import com.freshworks.SpringProject.teamMembers.TeamMembersJPA;
import com.freshworks.SpringProject.teamMembers.exceptions.TeamMemberNotFount;
import jdk.dynalink.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class InstancesController {

    @Autowired
    private InstancesJPA instancesJPA;

    @Autowired
    private InstanceHistory instanceHistory;
    @Autowired
    private InstanceHistoryJPA instanceHistoryJPA;
    @Autowired
    private InstancesMapper instancesMapper;
    @Autowired
    private TeamMembersJPA teamMembersJPA;

    @Autowired
    private TeamJPA teamJPA;

    @GetMapping("/v0/instance/{instance_id}")
    public Optional<Instances> getInstanceById(@PathVariable long instance_id){

        Optional<Instances> instance = instancesJPA.findById(instance_id);
        if(!instance.isPresent())
            throw new InstancesNotFoundException("Instance Not found Exceptions InstanceId:"+(instance_id));

        return instance;
    }

    @GetMapping("/v0/team/{team_id}/instance")
    public List<Instances> getInstanceByTeamId(@PathVariable long team_id){
        Optional<Team> team = teamJPA.findById(team_id);
        if (!team.isPresent()){
            throw new TeamNotFoundException("Team Not found team_id:"+team_id);
        }

        return instancesJPA.findByTeamId(team_id);
    }

    //Get available instances
    //Get running instances
    @GetMapping("/v0/team/{team_id}/instance/status")
    public List<Instances> getInstanceByTeamIdAndInUse(
            @PathVariable long team_id,
            @RequestParam boolean inUse){
        Optional<Team> team = teamJPA.findById(team_id);
        if (!team.isPresent()){
            throw new TeamNotFoundException("Team Not found team_id:"+team_id);
        }

        return instancesJPA.findByTeamIdAndInUse(team_id,inUse);
    }

    @PostMapping("v0/instance")
    public ResponseEntity<Object> createInstance(
            @RequestBody
            Instances instances){

        long teamID = instances.getTeamId();
        if (!((teamJPA.findById(teamID)).isPresent()))
            throw new TeamNotFoundException("Team Not found team_id:"+teamID);

        Instances instances_1 = instancesJPA.save(instances);
        return ResponseEntity.created(null).body(instancesJPA.findById(instances_1.getId()));
    }

    @DeleteMapping("/v0/instance/{instance_id}")
    public ResponseEntity<Object> deleteInstanceById(@PathVariable long instance_id){
        Optional<Instances> instance = instancesJPA.findById(instance_id);
        if(!instance.isPresent())
            throw new InstancesNotFoundException("Instance Not found Exceptions InstanceId:"+(instance_id));

        instancesJPA.deleteById(instance_id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/v0/instance/{instance_id}")
    public ResponseEntity<Object> updateInstance(
            @PathVariable long instance_id,
            @RequestBody InstancesDto instancesDto){

        Optional<Instances> instance = instancesJPA.findById(instance_id);
        if(!instance.isPresent())
            throw new InstancesNotFoundException("Instance Not found Exceptions InstanceId:"+(instance_id));

        long teamId = instancesDto.getTeamId();
        if (!((teamJPA.findById(teamId)).isPresent()))
            throw new TeamNotFoundException("Team Not found team_id:"+teamId);

        Instances instances = instancesMapper.dtoToModel(instancesDto);
        instances.setId(instance_id);
        instancesJPA.save(instances);
        return ResponseEntity.created(null).body(instancesJPA.findById(instance_id));
    }

    @GetMapping("/v0/instance/search")
    public List<Instances> getInstancesFromSearch(
            @RequestParam String searchTearm){
        return instancesJPA.findByInstanceNameContains(searchTearm);
    }

    @PostMapping("/v0/user/{team_member_id}/instances/{instance_id}")
    public ResponseEntity<Object> assignInstance(
            @RequestBody OperationDto operationDto,
            @PathVariable long team_member_id,
            @PathVariable long instance_id) {

        Optional<TeamMembers> teamMember = teamMembersJPA.findById(team_member_id);
        if(!teamMember.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);

        Optional<Instances> instance = instancesJPA.findById(instance_id);
        if(!instance.isPresent())
            throw new InstancesNotFoundException("Instance Not found Exceptions InstanceId:"+(instance_id));

        long team_id = (teamMember.get()).getTeamId();

        Instances instanceToBeFetched = instancesJPA.findByIdAndTeamId(instance_id,team_id);
        if(instanceToBeFetched == null)
            throw new InstanceNotUnderRequestedTeamException("Instance Not Under Requested Team InstanceId:"+(instance_id));

        if(operationDto.getOperation().contentEquals("start")){
            //Assigning instance
            if(instanceToBeFetched.isInUse()){
                throw new InstanceIsInUseException("instance is in use InstanceId"+instance_id);
            }
            instanceToBeFetched.setInUse(true);
            instancesJPA.save(instanceToBeFetched);

            instanceHistory.setId(0);
            instanceHistory.setInstanceId(instanceToBeFetched.getId());
            instanceHistory.setTeamId(team_id);
            instanceHistory.setTeamMemberId((teamMember.get()).getId());
            instanceHistory.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
            instanceHistory.setEndTime(null);
            instanceHistoryJPA.save(instanceHistory);

        }
        else if(operationDto.getOperation().contentEquals("stop")){
            //Stoping instance
            if(!instanceToBeFetched.isInUse()){
                throw new InstanceIsNotInUseException("instance is not in use InstanceId"+instance_id);
            }
            instanceToBeFetched.setInUse(false);
            instancesJPA.save(instanceToBeFetched);

            instanceHistory = instanceHistoryJPA.findByInstanceIdAndTeamMemberIdAndTeamIdAndEndTimeIsNull(
                                                instance_id,
                                                (teamMember.get()).getId(),
                                                team_id);
            instanceHistory.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
            instanceHistoryJPA.save(instanceHistory);
        }else {
                throw new NotValidOperationException("Not a valid operation, should be 'start' or 'stop'");
        }

        return ResponseEntity.created(null).body(instancesJPA.findById(instance_id));
    }
}
