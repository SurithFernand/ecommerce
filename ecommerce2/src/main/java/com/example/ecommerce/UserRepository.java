package com.example.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository is not needed here since this extends to JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
