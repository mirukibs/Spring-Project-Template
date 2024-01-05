package com.spring.SpringProjectTemplate.exceptions;

/**
 * Custom exception class for handling an invalid email format.
 *
 * This exception is thrown when an email address does not conform to the expected format.
 */
public class InvalidEmailFormatException extends IllegalArgumentException {

    /**
     * Constructs an {@code InvalidEmailFormatException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public InvalidEmailFormatException(String message) {
        super(message);
    }

}
