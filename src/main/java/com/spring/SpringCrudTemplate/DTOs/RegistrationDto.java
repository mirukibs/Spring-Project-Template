package com.spring.SpringCrudTemplate.DTOs;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
