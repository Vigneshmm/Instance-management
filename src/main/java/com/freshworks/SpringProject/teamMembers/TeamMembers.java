package com.freshworks.SpringProject.teamMembers;

import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_members")
public class TeamMembers {
    @Id
    private long id;
    @Column(name = "team_member_name")
    private String teamMemberName;
    @Column(name = "team_id")
    private long teamId;

    public TeamMembers(long id, String teamMemberName, long teamId) {
        this.id = id;
        this.teamMemberName = teamMemberName;
        this.teamId = teamId;
    }

    public TeamMembers() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamMemberName() {
        return teamMemberName;
    }

    public void setTeamMemberName(String teamMemberName) {
        this.teamMemberName = teamMemberName;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TeamMembers{" +
                "id=" + id +
                ", teamMemberName='" + teamMemberName + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
