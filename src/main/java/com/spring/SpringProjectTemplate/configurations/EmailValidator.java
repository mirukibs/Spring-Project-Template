package com.spring.SpringProjectTemplate.configurations;

import com.spring.SpringProjectTemplate.exceptions.EmailValidationException;
import com.spring.SpringProjectTemplate.exceptions.InvalidEmailFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating email addresses using a regular expression.
 */
public class EmailValidator {

    private static final Logger log = LoggerFactory.getLogger(EmailValidator.class);
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates the format of an email address.
     *
     * @param email The email address to validate
     * @throws InvalidEmailFormatException If the email format is invalid
     * @throws EmailValidationException    If an unexpected error occurs during validation
     */
    public static void validateEmail(String email) {
        try {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches()) {
                throw new InvalidEmailFormatException("Invalid email address format");
            }
        } catch (InvalidEmailFormatException e) {
            log.error("Invalid email address format: {}", email, e);
            throw e;
        } catch (Exception e) {
            log.error("Error validating email address: {}", email, e);
            throw new EmailValidationException("Email validation failed", e);
        }
    }
}
