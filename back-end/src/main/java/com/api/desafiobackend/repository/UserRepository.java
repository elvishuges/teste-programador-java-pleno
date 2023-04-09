package com.api.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.desafiobackend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
