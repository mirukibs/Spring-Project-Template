package com.spring.SpringCrudTemplate.services;

import com.spring.SpringCrudTemplate.models.AppUser;
import com.spring.SpringCrudTemplate.models.Role;
import com.spring.SpringCrudTemplate.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "User with email %s not found";
    private static final String EMAIL_ALREADY_TAKEN_MSG = "User registration failed. Email %s is already taken.";
    private static final String REGISTRATION_SUCCESSFUL_MSG = "User registered successfully. Email: %s";
    private static final String REGISTRATION_FAILED_MSG = "User registration failed unexpectedly. Email: %s";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            AppUser appUser = appUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

            return new User(appUser.getUsername(), appUser.getPassword(), mapRolesToAuthorities(appUser.getRoles()));
        } catch (Exception e) {
            log.error("Error during user retrieval for email: {}", email, e);
            throw e;
        }
    }

    public ResponseEntity<String> signUpUser(AppUser appUser) {
        if (userExists(appUser.getEmail())) {
            log.error(EMAIL_ALREADY_TAKEN_MSG, appUser.getEmail());
            return new ResponseEntity<>("Email already taken", HttpStatus.CONFLICT);
        }

        try {
            encodeAndSaveUser(appUser);
            log.info(REGISTRATION_SUCCESSFUL_MSG, appUser.getEmail());
            return new ResponseEntity<>("Congratulations! You have been registered successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(String.format(REGISTRATION_FAILED_MSG, appUser.getEmail()), e);
            return new ResponseEntity<>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean userExists(String email) {
        try {
            return appUserRepository.findByEmail(email).isPresent();
        } catch (Exception e) {
            log.error("Error checking if user exists for email: {}", email, e);
            throw e;
        }
    }

    private void encodeAndSaveUser(AppUser appUser) {
        try {
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            appUserRepository.save(appUser);
        } catch (Exception e) {
            log.error("Error encoding and saving user for email: {}", appUser.getEmail(), e);
            throw e;
        }
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}