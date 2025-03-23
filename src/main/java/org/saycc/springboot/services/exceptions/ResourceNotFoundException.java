package org.saycc.springboot.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Could not find user with id " + id);
    }
}
