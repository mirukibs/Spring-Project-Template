package com.spring.SpringCrudTemplate.DTOs;

import lombok.*;

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
