package com.spring.SpringCrudTemplate.DTOs;

import lombok.*;

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
