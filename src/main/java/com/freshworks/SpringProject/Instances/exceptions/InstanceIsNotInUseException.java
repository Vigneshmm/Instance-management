package com.freshworks.SpringProject.Instances.exceptions;

public class InstanceIsNotInUseException extends RuntimeException {
    public InstanceIsNotInUseException(String message) {
        super(message);
    }
}
