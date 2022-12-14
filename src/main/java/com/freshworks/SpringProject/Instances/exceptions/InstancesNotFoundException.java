package com.freshworks.SpringProject.Instances.exceptions;

public class InstancesNotFoundException extends RuntimeException {

    public InstancesNotFoundException(String message) {
        super(message);
    }
}
