package com.spring.SpringCrudTemplate.services;

import com.spring.SpringCrudTemplate.DTOs.RegistrationDTO;
import com.spring.SpringCrudTemplate.exceptions.RoleNotFoundException;
import com.spring.SpringCrudTemplate.exceptions.UserNotFoundException;
import com.spring.SpringCrudTemplate.models.AppUser;
import com.spring.SpringCrudTemplate.models.Role;
import com.spring.SpringCrudTemplate.repositories.AppUserRepository;
import com.spring.SpringCrudTemplate.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public ResponseEntity<String> registerUser(RegistrationDTO request) {
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
        } catch (UsernameNotFoundException e) {
            handleUserRetrievalError(userEmail, e);
            throw e; // Re-throw the custom exception to indicate the error
        }
    }

    private AppUser createAppUserFromRequest(RegistrationDTO request) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(request.getFirstname());
        appUser.setLastName(request.getLastname());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(request.getPassword());
        return appUser;
    }

    private Role getRoleByName() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new RoleNotFoundException("Role not found: USER"));
    }

    private AppUser findUserByEmail(String userEmail) {
        return appUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found after registration"));
    }

    private void handleUserRetrievalError(String userEmail, Exception e) {
        log.error("Error retrieving user ID after registration for email: {}", userEmail, e);
    }
}

