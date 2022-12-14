package com.freshworks.SpringProject.team;

import com.freshworks.SpringProject.team.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamJPA teamJPA;

    @GetMapping("/v0/teams")
    public List<Team> getTeams(){
        return teamJPA.findAll();
    }

    @GetMapping("/v0/teams/{team_id}")
    public Optional<Team> getTeamsById(@PathVariable long team_id){
        Optional<Team> team = teamJPA.findById(team_id);
        if (!team.isPresent()){
            throw new TeamNotFoundException("Team Not found team_id:"+team_id);
        }
        return team;
    }

    @DeleteMapping("v0/team/{team_id}")
    public ResponseEntity deleteById(@PathVariable long team_id){

        Optional<Team> team = teamJPA.findById(team_id);
        if (!team.isPresent()){
            throw new TeamNotFoundException("Team Not found team_id:"+team_id);
        }

        teamJPA.deleteById(team_id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("v0/team")
    public ResponseEntity<Object> createTeam(
            @RequestBody
            Team team ){
        Team Savedteam = teamJPA.save(team);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(Savedteam.getId())
                .toUri();
        return ResponseEntity.created(null).body(teamJPA.findById(Savedteam.getId()));
    }

    @PatchMapping("v0/team/{team_id}")
    public ResponseEntity<Object> updateTeam(
            @PathVariable long team_id,
            @RequestBody Team team
    ){
        Optional<Team> optionalTeam = teamJPA.findById(team_id);
        if (!optionalTeam.isPresent()){
            throw new TeamNotFoundException("team_id:"+team_id);
        }
        Team team_obj = optionalTeam.get();
        team_obj.setTeam_name(team.getTeam_name());
        teamJPA.save(team_obj);
        return ResponseEntity.created(null).body(teamJPA.findById(team_id));
    }
}
