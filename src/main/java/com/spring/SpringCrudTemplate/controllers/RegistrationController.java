package com.spring.SpringCrudTemplate.controllers;

import com.spring.SpringCrudTemplate.DTOs.AuthResponseDTO;
import com.spring.SpringCrudTemplate.DTOs.LoginDTO;
import com.spring.SpringCrudTemplate.DTOs.RegistrationDTO;
import com.spring.SpringCrudTemplate.configurations.JWT.JWTGenerator;
import com.spring.SpringCrudTemplate.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling user registration and login operations.
 */
@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    /**
     * Endpoint for user registration.
     *
     * @param userRequest The registration request DTO
     * @return ResponseEntity with the registration status and, if successful, a location header for the new user
     */
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegistrationDTO userRequest) {
        ResponseEntity<String> responseEntity = registrationService.registerUser(userRequest);

        // If registration is successful, create location header and return response
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            Long userId = registrationService.getSavedUserId(userRequest.getEmail());
            HttpHeaders headers = createLocationHeader(userId);
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(responseEntity.getBody());
        }

        // If registration is not successful, return the original response
        return responseEntity;
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequest The login request DTO
     * @return ResponseEntity with the authentication token if login is successful
     */
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticateUser(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    /**
     * Create a location header for the newly registered user.
     *
     * @param userId The user ID
     * @return HttpHeaders with the location header
     */
    private HttpHeaders createLocationHeader(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/users/" + userId);
        return headers;
    }

    /**
     * Authenticate the user based on login credentials.
     *
     * @param loginRequest The login request DTO
     * @return Authentication object if login is successful
     */
    private Authentication authenticateUser(LoginDTO loginRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
    }

}
