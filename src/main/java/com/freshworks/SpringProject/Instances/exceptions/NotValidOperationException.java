package com.freshworks.SpringProject.Instances.exceptions;

public class NotValidOperationException extends RuntimeException {
    public NotValidOperationException(String message) {
        super(message);
    }
}
