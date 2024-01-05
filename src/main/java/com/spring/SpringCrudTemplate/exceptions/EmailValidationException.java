package com.spring.SpringCrudTemplate.exceptions;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}