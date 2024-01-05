package com.spring.SpringProjectTemplate.repositories;

import com.spring.SpringProjectTemplate.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository interface for managing {@link AppUser} entities in the database.
 *
 * This repository extends JpaRepository, providing CRUD operations for AppUser entities.
 * The @Transactional annotation is applied at the class level to define read-only transactions.
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Retrieves an {@link Optional} containing an {@link AppUser} by their email.
     *
     * @param email The email of the user to be retrieved.
     * @return An Optional containing the AppUser, or empty if not found.
     */
    Optional<AppUser> findByEmail(String email);
}
