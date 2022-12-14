package com.freshworks.SpringProject.Instances.InstanceHistory;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "instances_history")
public class InstanceHistory {
    @Id
    private long id;
    @Column(name = "instance_id")
    private long instanceId;
    @Column(name = "team_member_id")
    private long teamMemberId;
    @Column(name = "team_id")
    private long teamId;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;

    public InstanceHistory() {
    }

    public InstanceHistory(long id, long instanceId, long teamMemberId, long teamId) {
        this.id = id;
        this.instanceId = instanceId;
        this.teamMemberId = teamMemberId;
        this.teamId = teamId;
    }
    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public long getTeamMemberId() {
        return teamMemberId;
    }

    public void setTeamMemberId(long teamMemberId) {
        this.teamMemberId = teamMemberId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "InstanceHistory{" +
                "id=" + id +
                ", instanceId=" + instanceId +
                ", teamMemberId=" + teamMemberId +
                ", teamId=" + teamId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
