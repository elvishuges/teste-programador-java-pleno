package com.api.desafiobackend.services;

import com.api.desafiobackend.dto.OrderedDTO;
import com.api.desafiobackend.entity.*;
import com.api.desafiobackend.repository.*;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderedService {

    private OrderedRepository orderedRepository;
    private ProductRepository productRepository;

    public OrderedService(OrderedRepository orderedRepository, ProductRepository productRepository) {
        this.orderedRepository = orderedRepository;
        this.productRepository = productRepository;
    }

    public Ordered saveOrdered(OrderedDTO o) {
        List<Product> products = o.getProducts().stream().map(name -> this.preloadTagByCode(name))
                .collect(Collectors.toList());
        System.out.println(products);
        Ordered newOrdered = new Ordered(o.getDescription(), products);
        return orderedRepository.save(newOrdered);
    }

    public List<Ordered> findAll() {
        return orderedRepository.findAll();
    }

    public Ordered findOrderedById(Long orderedId) {
        Optional<Ordered> userOptional = orderedRepository.findById(orderedId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public void deleteOrdered(Long orderedId) {
        orderedRepository.deleteById(orderedId);

    }

    private Product preloadTagByCode(String code) {
        Product product = productRepository.findByCode(code);
        return product;
    }

}