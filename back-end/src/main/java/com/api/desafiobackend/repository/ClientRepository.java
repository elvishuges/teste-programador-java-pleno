package com.api.desafiobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.desafiobackend.entity.*;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
