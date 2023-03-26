package com.api.desafiobackend.services;

import com.api.desafiobackend.entity.*;
import com.api.desafiobackend.repository.*;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product p) {
        return productRepository.save(p);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findProductById(Long productId) {
        Optional<Product> userOptional = productRepository.findById(productId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);

    }

    public Set<Product> getProductsByOrderId(Long productId) {
        return productRepository.findByOrderedId(productId);

    }

}