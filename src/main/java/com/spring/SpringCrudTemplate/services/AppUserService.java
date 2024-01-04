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
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);


    @Override
    public UserDetails loadUserByUsername(String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

        return new User(appUser.getUsername(), appUser.getPassword(), mapRolesToAuthorities(appUser.getRoles()));
    }

    public ResponseEntity<String> signUpUser(AppUser appUser) {
        if (userExists(appUser.getEmail())) {
            log.error("User registration failed. Email {} is already taken.", appUser.getEmail());
            return new ResponseEntity<>("Email already taken", HttpStatus.CONFLICT);
        }

        try {
            encodeAndSaveUser(appUser);
            log.info("User registered successfully. Email: {}", appUser.getEmail());
            return new ResponseEntity<>("Congratulations! You have been registered successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("User registration failed unexpectedly. Email: {}", appUser.getEmail(), e);
            return new ResponseEntity<>("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean userExists(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    private void encodeAndSaveUser(AppUser appUser) {
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}