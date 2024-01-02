package com.spring.SpringCrudTemplate.DTOs;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginDto {

    private String username;
    private String password;

}
