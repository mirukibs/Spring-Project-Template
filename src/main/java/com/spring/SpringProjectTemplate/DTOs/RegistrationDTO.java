package com.spring.SpringProjectTemplate.DTOs;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing user registration information.
 *
 * This class is used to transfer registration details between the controller and service layers.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
