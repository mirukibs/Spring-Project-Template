package com.spring.SpringProjectTemplate.configurations.security;

import com.spring.SpringProjectTemplate.models.Role;
import com.spring.SpringProjectTemplate.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing predefined roles in the application.
 * You could create a mechanism for an admin to create roles dynamically,
 * but for the sake of simplicity, we will just create a few predefined roles.
 */
@Configuration
public class RoleConfig {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Initializes predefined roles during application startup.
     */
    @PostConstruct
    public void initRoles() {
        createRoleIfNotExists("USER");
        createRoleIfNotExists("ADMIN");
        // Add more predefined roles as needed
    }


    /**
     * Creates a role if it does not already exist in the repository.
     *
     * @param roleName The name of the role to be created
     */
    private void createRoleIfNotExists(String roleName) {
        if (!roleRepository.existsByName(roleName)) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }

}