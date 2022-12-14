package com.freshworks.SpringProject.Instances.exceptions;

public class InstanceIsInUseException extends RuntimeException {
    public InstanceIsInUseException(String message) {
        super(message);
    }
}
