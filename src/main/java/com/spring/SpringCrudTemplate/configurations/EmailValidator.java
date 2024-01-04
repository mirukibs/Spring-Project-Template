package com.spring.SpringCrudTemplate.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final Logger log = LoggerFactory.getLogger(EmailValidator.class);

    private static final String EMAIL_REGEX =
            "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static void validateEmail(String email) {
        try {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid email address format");
            }
        } catch (Exception e) {
            log.error("Error validating email address: {}", email, e);
            throw e; // rethrow the exception after logging
        }
    }
}
