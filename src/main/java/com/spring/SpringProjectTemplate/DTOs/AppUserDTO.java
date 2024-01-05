package com.spring.SpringProjectTemplate.DTOs;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing an application user.
 * This class serves as a simplified version of the AppUser entity for data exchange.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppUserDTO {

    private Long userID;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
