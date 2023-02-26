package com.api.desafiobackend.controllers;

import com.api.desafiobackend.services.OrderedService;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.desafiobackend.models.*;
import com.api.desafiobackend.dto.OrderedDTO;
import com.api.desafiobackend.entity.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class OrderedController {

    @Autowired
    OrderedService orderedService;

    @PostMapping(value = "/ordered")
    public ResponseEntity<?> createNewOrdered(@RequestBody OrderedDTO orderedDTO) {

        Ordered createdOrdered = orderedService.saveOrdered(orderedDTO);
        return new ResponseEntity<Object>(createdOrdered, HttpStatus.CREATED);
    }

    @GetMapping("/ordereds")
    public ResponseEntity<?> getAllOrdereds() {
        List<Ordered> ordereds = orderedService.findAll();

        return new ResponseEntity<Object>(ordereds, HttpStatus.OK);
    }

}