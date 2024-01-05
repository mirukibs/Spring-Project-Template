package com.spring.SpringProjectTemplate.repositories;

import com.spring.SpringProjectTemplate.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for managing {@link Role} entities in the database.
 *
 * This repository extends JpaRepository, providing CRUD operations for Role entities.
 * The @Transactional annotation is applied at the class level to define read-only transactions.
 */
@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Retrieves an {@link Optional} containing a {@link Role} by its name.
     *
     * @param name The name of the role to be retrieved.
     * @return An Optional containing the Role, or empty if not found.
     */
    Optional<Role> findByName(String name);


    /**
     * Checks if a role with the given name exists in the database.
     *
     * @param name The name of the role to check for existence.
     * @return True if the role exists, false otherwise.
     */
    boolean existsByName(String name);
}
