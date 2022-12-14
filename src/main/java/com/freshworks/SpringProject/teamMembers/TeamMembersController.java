package com.freshworks.SpringProject.teamMembers;

import com.freshworks.SpringProject.team.Team;
import com.freshworks.SpringProject.team.TeamJPA;
import com.freshworks.SpringProject.team.exceptions.TeamNotFoundException;
import com.freshworks.SpringProject.teamMembers.exceptions.TeamMemberNotFount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamMembersController {

    @Autowired
    private TeamMembersJPA teamMembersJPA;

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Autowired
    private TeamJPA teamJPA;

    @GetMapping("v0/teamMembers")
    public List<TeamMembers> getAllTeamMembers(){
        return teamMembersJPA.findAll();
    }

    @GetMapping("v0/teamMembers/{team_member_id}")
    public Optional<TeamMembers> getTeamMembersById(
            @PathVariable long team_member_id){
        Optional<TeamMembers> teamMember = teamMembersJPA.findById(team_member_id);
        if(!teamMember.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);
        return teamMember;
    }

    @GetMapping("v0/team/{team_id}/teamMembers")
    public List<TeamMembers> getTeamMembersByTeamId(
            @PathVariable long team_id){
        Optional<Team> team = teamJPA.findById(team_id);
        if (!team.isPresent()){
            throw new TeamNotFoundException("Team Not found team_id:"+team_id);
        }
        return teamMembersJPA.findByTeamId(team_id);
    }

    @PostMapping("v0/teamMembers")
    public ResponseEntity<TeamMembers> createTeamMembers(
            @RequestBody
            TeamMembers teamMembers ){

        long teamID = teamMembers.getTeamId();
        if (!((teamJPA.findById(teamID)).isPresent()))
            throw new TeamNotFoundException("Team Not found team_id:"+teamID);

        TeamMembers createdMember = teamMembersJPA.save(teamMembers);
        return ResponseEntity.created(null).body(createdMember);
    }

    @DeleteMapping("v0/teamMembers/{team_member_id}")
    public ResponseEntity deleteTeamMember(@PathVariable long team_member_id){

        Optional<TeamMembers> teamMember = teamMembersJPA.findById(team_member_id);
        if(!teamMember.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);

        teamMembersJPA.deleteById(team_member_id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("v0/teamMembers/{team_member_id}")
    public ResponseEntity<Object> updateTeamMember(
            @PathVariable long team_member_id,
            @RequestBody TeamMembersDto teamMembersDto
    ){
        Optional<TeamMembers> teamMemberCheck = teamMembersJPA.findById(team_member_id);
        if(!teamMemberCheck.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);

        long teamID = teamMembersDto.getTeamId();
        if (!((teamJPA.findById(teamID)).isPresent()))
            throw new TeamNotFoundException("Team Not found team_id:"+teamID);

        TeamMembers teamMember = teamMemberMapper.dtoToModel(teamMembersDto);
        teamMember.setId(team_member_id);
        teamMembersJPA.save(teamMember);
        return ResponseEntity.created(null).body(teamMembersJPA.findById(team_member_id));
    }
}
