package com.example.jft_pr16.repository;

import com.example.jft_pr16.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //!
    Optional<User> findByUsername(String username);
}
