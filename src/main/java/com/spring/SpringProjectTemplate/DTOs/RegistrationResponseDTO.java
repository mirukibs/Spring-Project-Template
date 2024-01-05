package com.spring.SpringProjectTemplate.DTOs;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing the response to a registration.
 *
 * This class encapsulates the details of a registration response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationResponseDTO {
    private String message;
    private String userEmail;

}