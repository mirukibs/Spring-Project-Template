package com.spring.SpringProjectTemplate.DTOs;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an authentication response.
 *
 * This class encapsulates the details of an authentication response, providing a standardized
 * structure for conveying authentication details.
 */
@Data
public class AuthResponseDTO {

    private String message;

    private String accessToken;

    private String tokenType = "Bearer ";

    /**
     * Constructs an instance of AuthResponseDTO with the provided access token.
     *
     * @param accessToken The access token issued during authentication
     */
    public AuthResponseDTO(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }
}
