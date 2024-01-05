package com.spring.SpringProjectTemplate.configurations.security;

import com.spring.SpringProjectTemplate.configurations.JWT.JWTAuthenticationFilter;
import com.spring.SpringProjectTemplate.configurations.JWT.JWTAuthEntryPoint;
import com.spring.SpringProjectTemplate.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class contains the main security configurations
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    private final AppUserService appUserService;
    private final JWTAuthEntryPoint authEntryPoint;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Configures the security filter chain for the application.
     *
     * @param http The HTTP security configuration
     * @return The configured security filter chain
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authEntryPoint)
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .invalidSessionUrl("/invalid-session")
                )
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "api/v1/**",
                            "/v3/api-docs",
                            "/v3/api-docs/**",
                            "/configuration/ui",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // JWT filter first for REST API

        return http.build();
    }


    /**
     * Configures the authentication manager for the application.
     *
     * @param authenticationConfiguration The authentication configuration
     * @return The configured authentication manager
     * @throws Exception If an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * Configures the DAO authentication provider for the application.
     *
     * @return The configured DAO authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }


    /**
     * Creates and configures the JWT authentication filter.
     *
     * @return The configured JWT authentication filter
     */
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

}
