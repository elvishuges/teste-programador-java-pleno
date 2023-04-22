package com.api.desafiobackend.controllers;

import com.api.desafiobackend.services.OrderedService;
import com.api.desafiobackend.services.ProductService;
import com.fasterxml.jackson.annotation.JsonView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.desafiobackend.models.*;
import com.api.desafiobackend.dto.OrderedDTO;
import com.api.desafiobackend.entity.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class OrderedController {

    @Autowired
    OrderedService orderedService;
    @Autowired
    ProductService productService;

    @PostMapping(value = "/ordered")
    public ResponseEntity<?> createNewOrdered(@RequestBody OrderedDTO orderedDTO) {

        Ordered createdOrdered = orderedService.saveOrdered(orderedDTO);
        return new ResponseEntity<Object>(createdOrdered, HttpStatus.CREATED);
    }

    @GetMapping(value = "/ordered/{id}")
    public ResponseEntity<?> getOrderedtById(@PathVariable("id") Long orderedId) {
        Ordered ordered = orderedService.findOrderedById(orderedId);
        if (ordered == null) {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessage("Ordered not found.");
            return new ResponseEntity<Object>(messageResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(ordered, HttpStatus.OK);
    }

    @GetMapping("/ordereds")
    public ResponseEntity<?> getAllOrdereds(@RequestParam(required = false) boolean viewProducts) {
        List<Ordered> orders = orderedService.findAll();
        if (!viewProducts) {
            for (Ordered order : orders) {
                order.setProducts(null);
            }
        }
        return ResponseEntity.ok(orders);
    }

    @PutMapping(value = "/ordered/{id}")
    public ResponseEntity<?> updateOrdered(@PathVariable("id") Long orderedId, @RequestBody OrderedDTO o) {
        Ordered ordered = orderedService.findOrderedById(orderedId);
        if (ordered == null) {
            return ResponseEntity.notFound().build();
        }
        ordered.setNumber(o.getNumber());
        ordered.setDescription(o.getDescription());
        orderedService.saveOrdered(o);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Ordered has been updated successfully.");
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/ordered/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long orderedId) {
        orderedService.deleteOrdered(orderedId);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Product has been deleted successfully.");
        return new ResponseEntity<Object>(messageResponse, HttpStatus.OK);
    }

    // private Ordered convertToEntity(OrderedDTO orderedDTO) throws ParseException
    // {
    // Ordered ordered = modelMapper.map(orderedDTO, Ordered.class);

    // if (orderedDTO != null) {
    // Ordered oldOrdered = orderedService.findOrderedById(orderedDTO.getId());
    // ordered.setRedditID(oldOrdered.getRedditID());
    // ordered.setSent(oldOrdered.isSent());
    // }
    // return ordered;
    // }

}