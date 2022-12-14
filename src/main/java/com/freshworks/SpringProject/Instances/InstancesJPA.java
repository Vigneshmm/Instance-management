package com.freshworks.SpringProject.Instances;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstancesJPA extends JpaRepository<Instances, Long> {
    List<Instances> findByTeamId(long teamId);
    List<Instances> findByTeamIdAndInUse(long teamId, boolean inUse);
    List<Instances> findByInstanceNameContains(String contains);
    Instances findByIdAndTeamId(long id, long teamId);
}
