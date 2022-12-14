package com.freshworks.SpringProject.Instances;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instances {
    @Id
    private long id;
    @Column(name = "instance_name")
    private String instanceName;
    @Column(name = "team_id")
    private long teamId;
    @Column(name = "ram")
    private long ram;
    @Column(name = "cpu")
    private long cpu;
    @Column(name = "in_use")
    private boolean inUse;

    public Instances() {
    }

    public Instances(long id, String instanceName, long teamId, long ram, long cpu) {
        this.id = id;
        this.instanceName = instanceName;
        this.teamId = teamId;
        this.ram = ram;
        this.cpu = cpu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getRam() {
        return ram;
    }

    public void setRam(long ram) {
        this.ram = ram;
    }

    public long getCpu() {
        return cpu;
    }

    public void setCpu(long cpu) {
        this.cpu = cpu;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
