package com.spring.SpringCrudTemplate.services;

import com.spring.SpringCrudTemplate.DTOs.RegistrationDto;
import com.spring.SpringCrudTemplate.models.AppUser;
import com.spring.SpringCrudTemplate.models.Role;
import com.spring.SpringCrudTemplate.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final AppUserService appUserService;
    @Autowired
    private final RoleRepository roleRepository;

    public String registerUser(RegistrationDto request) {
        // TODO: Validate email

        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstname());
        appUser.setLastName(request.getLastname());
        appUser.setEmail(request.getUsername());
        appUser.setPassword(request.getPassword());
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found: USER"));
        appUser.setRoles(Collections.singletonList(role));

        return appUserService.signUpUser(appUser);
    }

}

