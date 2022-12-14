package com.freshworks.SpringProject.Instances.InstanceHistory;

import com.freshworks.SpringProject.teamMembers.TeamMembers;
import com.freshworks.SpringProject.teamMembers.TeamMembersJPA;
import com.freshworks.SpringProject.teamMembers.exceptions.TeamMemberNotFount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class InstanceHistoryContorller {

    @Autowired
    private InstanceHistoryJPA instanceHistoryJPA;

    @Autowired
    TeamMembersJPA teamMembersJPA;


    @GetMapping("/v0/user/history/pagination")
    private Page<InstanceHistory> getInstanceHistoryPagination(
            @RequestParam int page,
            @RequestParam int pageSize ){
        Page<InstanceHistory> instanceHistoryPage = instanceHistoryJPA.findAll(PageRequest.of(page, pageSize));
        return instanceHistoryPage;
    }

    @GetMapping("/v0/instances/running")
    private List<InstanceHistory> getRunningInstanceByTeamId(){
        return instanceHistoryJPA.findByEndTimeIsNull();
    }

    @GetMapping("/v0/user/{team_member_id}/history")
    private List<InstanceHistory> getInstanceHistoryByTeamMemberId(@PathVariable long team_member_id){
        Optional<TeamMembers> teamMember = teamMembersJPA.findById(team_member_id);
        if(!teamMember.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);

        return instanceHistoryJPA.findByTeamMemberId(team_member_id);
    }

    @GetMapping("/v0/user/{team_member_id}/history/pagination")
    private Optional<List<InstanceHistory>> getInstanceHistoryByTeamMemberIdWithPagination(
            @PathVariable long team_member_id,
            @RequestParam(required = false, defaultValue = "0") int cursor,
            @RequestParam int pageSize ){
        Optional<TeamMembers> teamMember = teamMembersJPA.findById(team_member_id);
        if(!teamMember.isPresent())
            throw new TeamMemberNotFount("Team member not found team_member_id:"+team_member_id);

        Optional<List<InstanceHistory>> instanceHistories = instanceHistoryJPA.findByTeamMemberIdWithLimit(team_member_id,cursor,pageSize);
        return instanceHistories;
    }
}
