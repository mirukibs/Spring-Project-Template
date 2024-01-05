package com.spring.SpringProjectTemplate.exceptions;

/**
 * Custom runtime exception thrown when a requested role is not found.
 * This exception is intended to capture and handle errors related to role retrieval.
 */
public class RoleNotFoundException extends RuntimeException {

    /**
     * Constructs a RoleNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public RoleNotFoundException(String message) {
        super(message);
    }
}
