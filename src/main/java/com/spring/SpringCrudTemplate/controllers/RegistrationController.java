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

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService registrationService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegistrationDTO userRequest) {
        ResponseEntity<String> responseEntity = registrationService.registerUser(userRequest);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            Long userId = registrationService.getSavedUserId(userRequest.getEmail());
            HttpHeaders headers = createLocationHeader(userId);
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(responseEntity.getBody());
        }

        return responseEntity;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticateUser(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }


    private HttpHeaders createLocationHeader(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/users/" + userId);
        return headers;
    }


    private Authentication authenticateUser(LoginDTO loginRequest) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
    }

}
