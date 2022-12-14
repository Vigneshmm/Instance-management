package com.freshworks.SpringProject.Instances.exceptions;

public class InstanceNotUnderRequestedTeamException extends RuntimeException {
    public InstanceNotUnderRequestedTeamException(String message) {
        super (message);
    }
}
