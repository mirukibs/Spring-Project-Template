package com.spring.SpringCrudTemplate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents an application user in the system.
 *
 * This class serves as a template for creating user entities with associated roles.
 * Extend or modify this class based on your specific project requirements.
 *
 * Class Overview:
 * - Annotated with JPA annotations for persistence.
 * - Utilizes Lombok annotations to automatically generate getters, setters, constructors, and other boilerplate code.
 * - Includes fields for user details such as first name, last name, email, password, and associated roles.
 * - Defines a sequence generator for generating unique user IDs.
 *
 * Note: Developers should review and adapt this class based on project-specific requirements and best practices.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public AppUser(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }
}