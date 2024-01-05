package com.spring.SpringCrudTemplate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a role in the system.
 *
 * This class serves as a template for creating role entities.
 * Extend or modify this class based on your specific project requirements.
 *
 * Class Overview:
 * - Annotated with JPA annotations for persistence.
 * - Utilizes Lombok annotations to automatically generate getters, setters, and constructors.
 * - Includes fields for role details such as ID and name.
 *
 * Note: Developers should review and adapt this class based on project-specific requirements and best practices.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

}