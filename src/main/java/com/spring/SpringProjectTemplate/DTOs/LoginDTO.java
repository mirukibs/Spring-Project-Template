package com.spring.SpringProjectTemplate.DTOs;

import lombok.*;

/**
 * Data Transfer Object (DTO) representing login credentials for authentication.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginDTO {

    private String username;
    private String password;

}
