package com.spring.SpringProjectTemplate.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a role entity in the system.
 *
 * This class is annotated with Lombok annotations for automatic generation of getter,
 * setter, and no-args constructor. It is also annotated as an entity to be mapped to a database table.
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
