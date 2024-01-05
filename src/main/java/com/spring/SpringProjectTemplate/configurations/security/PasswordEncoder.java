package com.spring.SpringProjectTemplate.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class to provide a BCryptPasswordEncoder bean for password encoding.
 */
@Configuration
public class PasswordEncoder {

    /**
     * Creates and returns a BCryptPasswordEncoder bean.
     *
     * @return BCryptPasswordEncoder bean for password encoding
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}