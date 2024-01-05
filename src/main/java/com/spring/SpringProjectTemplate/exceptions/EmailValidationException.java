package com.spring.SpringProjectTemplate.exceptions;

/**
 * Custom runtime exception thrown for email validation errors.
 * This exception is intended to capture and handle errors related to email validation.
 */
public class EmailValidationException extends RuntimeException {

    /**
     * Constructs an EmailValidationException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   The cause (which is saved for later retrieval by the getCause() method)
     */
    public EmailValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
