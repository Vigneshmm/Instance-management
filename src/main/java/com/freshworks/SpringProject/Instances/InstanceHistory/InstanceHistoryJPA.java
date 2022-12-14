package com.freshworks.SpringProject.Instances.InstanceHistory;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InstanceHistoryJPA extends JpaRepository<InstanceHistory, Long> {
    List<InstanceHistory> findByTeamMemberId(long teamMemberId);
    List<InstanceHistory> findByEndTimeIsNull();
    InstanceHistory findByInstanceIdAndTeamMemberIdAndTeamIdAndEndTimeIsNull(long instanceId, long teamMemberId, long teamId);

    @Query(value = "SELECT * FROM instances_history IHccusr WHERE team_member_id = ?1 "
            + " AND id > ?2 ORDER BY IHccusr.id LIMIT ?3", nativeQuery = true)
    Optional<List<InstanceHistory>> findByTeamMemberIdWithLimit(long team_member_id, int cursor, int limit);
}
