package com.spring.SpringProjectTemplate.services;

import com.spring.SpringProjectTemplate.DTOs.RegistrationResponseDTO;
import com.spring.SpringProjectTemplate.models.AppUser;
import com.spring.SpringProjectTemplate.models.Role;
import com.spring.SpringProjectTemplate.repositories.AppUserRepository;
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

/**
 * Service class handling user operations, implementing UserDetailsService for Spring Security integration.
 */
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

    /**
     * Load user details by email for Spring Security authentication.
     *
     * @param email The email of the user to be loaded.
     * @return UserDetails object for authentication.
     * @throws UsernameNotFoundException If the user with the given email is not found.
     */
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


    /**
     * Sign up a new user and handle the registration process.
     *
     * @param appUser The user to be registered.
     * @return ResponseEntity with the registration status and message.
     */
    public ResponseEntity<RegistrationResponseDTO> signUpUser(AppUser appUser) {
        if (userExists(appUser.getEmail())) {
            log.error(EMAIL_ALREADY_TAKEN_MSG, appUser.getEmail());
            return new ResponseEntity<>(new RegistrationResponseDTO("Email already taken", null), HttpStatus.CONFLICT);
        }

        try {
            encodeAndSaveUser(appUser);
            log.info(REGISTRATION_SUCCESSFUL_MSG, appUser.getEmail());
            RegistrationResponseDTO responseDTO = new RegistrationResponseDTO(
                    "Congratulations! You have been registered successfully.", appUser.getEmail());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(String.format(REGISTRATION_FAILED_MSG, appUser.getEmail()), e);
            return new ResponseEntity<>(new RegistrationResponseDTO("Internal Server Error.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Checks if a user with the given email already exists in the system.
     *
     * @param email The email to check for existence.
     * @return true if a user with the given email exists, false otherwise.
     * @throws RuntimeException If an unexpected error occurs during the check.
     */
    private boolean userExists(String email) {
        try {
            return appUserRepository.findByEmail(email).isPresent();
        } catch (Exception e) {
            log.error("Error checking if user exists for email: {}", email, e);
            throw e;
        }
    }


    /**
     * Encodes the user's password using BCrypt and saves the user to the repository.
     *
     * @param appUser The user to be encoded and saved.
     * @throws RuntimeException If an unexpected error occurs during password encoding or user saving.
     */
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


    /**
     * Maps the roles of a user to Spring Security GrantedAuthorities.
     *
     * @param roles The list of roles associated with a user.
     * @return Collection of GrantedAuthorities representing the roles.
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
