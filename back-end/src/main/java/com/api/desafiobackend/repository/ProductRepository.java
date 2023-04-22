package com.api.desafiobackend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.desafiobackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);

    Set<Product> findByOrderedId(Long orderId);
}
