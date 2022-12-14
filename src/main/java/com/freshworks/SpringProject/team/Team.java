package com.freshworks.SpringProject.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    private long id;
    @Column(name = "team_name")
    private String team_name;

    public Team() {
    }

    public Team( String team_name) {
        this.team_name = team_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    @Override
    public String toString() {
        return "team{" +
                "id=" + id +
                ", team_name='" + team_name + '\'' +
                '}';
    }
}
