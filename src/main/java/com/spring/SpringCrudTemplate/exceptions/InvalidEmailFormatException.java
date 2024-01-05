package com.spring.SpringCrudTemplate.exceptions;

public class InvalidEmailFormatException extends IllegalArgumentException {
    public InvalidEmailFormatException(String message) {
        super(message);
    }
}
