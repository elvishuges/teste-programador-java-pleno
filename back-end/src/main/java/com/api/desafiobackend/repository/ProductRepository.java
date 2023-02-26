package com.api.desafiobackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.desafiobackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
