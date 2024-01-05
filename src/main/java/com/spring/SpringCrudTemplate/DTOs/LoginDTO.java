package com.spring.SpringCrudTemplate.DTOs;

import lombok.*;

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
