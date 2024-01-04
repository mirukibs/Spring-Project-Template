package com.spring.SpringCrudTemplate.controllers;

import com.spring.SpringCrudTemplate.DTOs.AuthResponseDTO;
import com.spring.SpringCrudTemplate.DTOs.LoginDto;
import com.spring.SpringCrudTemplate.DTOs.RegistrationDto;
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
    public ResponseEntity<String> register(@RequestBody RegistrationDto userRequest) {
        ResponseEntity<String> responseEntity = registrationService.registerUser(userRequest);

        // Check if the registration was successful (status code 201)
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {

            // Extract the user ID from the response body or generate it based on your logic
            Long userId = registrationService.getSavedUserId(userRequest.getEmail());

            // Create the Location header
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, "/users/" + userId);

            // Combine the headers and body from the registration service response
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(responseEntity.getBody());
        }

        // If registration was not successful, return the original response
        return responseEntity;
    }


    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
