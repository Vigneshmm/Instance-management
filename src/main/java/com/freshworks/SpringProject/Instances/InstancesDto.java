package com.freshworks.SpringProject.Instances;

import javax.persistence.Column;

public class InstancesDto {
    private String instanceName;
    private long teamId;
    private long ram;
    private long cpu;
    private boolean inUse;

    public InstancesDto(String instanceName, long teamId, long ram, long cpu, boolean inUse) {
        this.instanceName = instanceName;
        this.teamId = teamId;
        this.ram = ram;
        this.cpu = cpu;
        this.inUse = inUse;
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

    public InstancesDto() {
    }

}
