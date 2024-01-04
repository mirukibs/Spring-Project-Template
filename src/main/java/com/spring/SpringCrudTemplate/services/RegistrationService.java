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

import static com.spring.SpringCrudTemplate.configurations.EmailValidator.validateEmail;

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
        validateEmail(request.getEmail());

        AppUser appUser = createAppUserFromRequest(request);
        Role role = getRoleByName();
        appUser.setRoles(Collections.singletonList(role));

        return appUserService.signUpUser(appUser);
    }

    public Long getSavedUserId(String userEmail) {
        try {
            AppUser savedUser = findUserByEmail(userEmail);
            return savedUser.getUserID();
        } catch (Exception e) {
            handleUserRetrievalError(userEmail, e);
            return -1L; // You might want to throw an exception or handle it differently in a production scenario
        }
    }


    private AppUser createAppUserFromRequest(RegistrationDto request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstname());
        appUser.setLastName(request.getLastname());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(request.getPassword());
        return appUser;
    }

    private Role getRoleByName() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found: " + "USER"));
    }

    private AppUser findUserByEmail(String userEmail) {
        return appUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("User not found after registration"));
    }

    private void handleUserRetrievalError(String userEmail, Exception e) {
        log.error("Error retrieving user ID after registration for email: {}", userEmail, e);
    }
}

