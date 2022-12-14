package com.freshworks.SpringProject.teamMembers;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamMembersJPA extends JpaRepository<TeamMembers, Long> {

    List<TeamMembers> findByTeamId(long team_id);

}
