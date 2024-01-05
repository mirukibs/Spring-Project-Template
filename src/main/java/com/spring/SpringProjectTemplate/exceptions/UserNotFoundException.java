package com.spring.SpringProjectTemplate.exceptions;

/**
 * A custom runtime exception thrown when a requested user is not found.
 * This exception is intended to capture and handle errors related to user retrieval.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a UserNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
