package com.spring.SpringProjectTemplate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing an application user.
 * This class is mapped to the database table "app_user" using JPA annotations.
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

    /**
     * Many-to-Many relationship with roles.
     * EAGER fetching is used for immediate loading of roles.
     * CascadeType.ALL ensures that operations on AppUser cascade to associated roles.
     */
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


    /**
     * Getter method for obtaining the username (which is the email address).
     *
     * @return The email address as the username
     */
    public String getUsername() {
        return email;
    }
}
