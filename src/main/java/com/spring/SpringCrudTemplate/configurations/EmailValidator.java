package com.spring.SpringCrudTemplate.configurations;

import com.spring.SpringCrudTemplate.exceptions.EmailValidationException;
import com.spring.SpringCrudTemplate.exceptions.InvalidEmailFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final Logger log = LoggerFactory.getLogger(EmailValidator.class);
    private static final String EMAIL_REGEX = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

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
