package com.example.Springsecurityemail.repository;

import com.example.Springsecurityemail.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
}
