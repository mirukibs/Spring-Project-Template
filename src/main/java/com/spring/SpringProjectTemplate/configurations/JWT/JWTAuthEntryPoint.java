package com.spring.SpringProjectTemplate.configurations.JWT;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Custom authentication entry point for handling unauthorized access.
 * This class is responsible for responding to unauthorized requests with a 401 Unauthorized status and an error message.
 */
@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called when an unauthorized request is made.
     *
     * @param request       The HTTP request that resulted in an authentication failure.
     * @param response      The HTTP response to which the error response should be written.
     * @param authException The exception that caused the authentication failure.
     * @throws IOException      If an input or output exception occurs.
     * @throws ServletException If a servlet exception occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Send a 401 Unauthorized status with the exception message as the response body
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}