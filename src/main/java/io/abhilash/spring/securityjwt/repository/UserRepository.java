package io.abhilash.spring.securityjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.abhilash.spring.securityjwt.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    // JPA automaitcally finds the user after we specify this method
    // Clues which we give are return Type would be User, and we are passing a String username
}
