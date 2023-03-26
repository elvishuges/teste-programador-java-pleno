package com.api.desafiobackend.controllers;

import com.api.desafiobackend.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.desafiobackend.models.*;

import com.api.desafiobackend.entity.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product")
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        Product createdProduct = productService.saveProduct(product);
        return new ResponseEntity<Object>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.findAll();

        return new ResponseEntity<Object>(products, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Product has been deleted successfully.");
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productId) {
        Product product = productService.findProductById(productId);
        if (product == null) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessage("Product not found.");
            return new ResponseEntity<Object>(messageResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(product, HttpStatus.OK);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long productId, @RequestBody Product p) {
        Product product = productService.findProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setCode(p.getCode());
        product.setDescription(p.getDescription());
        product.setUnit(p.getUnit());
        product.setValue(p.getValue());
        productService.saveProduct(product);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Product has been updated successfully.");
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

}