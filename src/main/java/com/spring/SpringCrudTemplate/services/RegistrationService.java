package com.spring.SpringCrudTemplate.services;

import com.spring.SpringCrudTemplate.DTOs.RegistrationDto;
import com.spring.SpringCrudTemplate.models.AppUser;
import com.spring.SpringCrudTemplate.models.Role;
import com.spring.SpringCrudTemplate.repositories.AppUserRepository;
import com.spring.SpringCrudTemplate.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private final AppUserService appUserService;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final AppUserRepository appUserRepository;
    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);


    public ResponseEntity<String> registerUser(RegistrationDto request) {
        // TODO: Validate email

        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstname());
        appUser.setLastName(request.getLastname());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(request.getPassword());
        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found: USER"));
        appUser.setRoles(Collections.singletonList(role));

        return appUserService.signUpUser(appUser);
    }

    // Method to retrieve the user ID after saving the AppUser instance
    public Long getSavedUserId(String userEmail) {
        try {
            AppUser savedUser = appUserRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new IllegalStateException("User not found after registration"));

            return savedUser.getUserID();
        } catch (Exception e) {
            // Log the exception and return a default or meaningful value
            log.error("Error retrieving user ID after registration for email: {}", userEmail, e);

            return -1L;
        }
    }

}

