package com.api.desafiobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.desafiobackend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
