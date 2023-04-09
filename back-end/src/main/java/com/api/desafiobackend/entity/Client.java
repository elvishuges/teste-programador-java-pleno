package com.api.desafiobackend.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;
    private String name;
    private String phone;
    private String email;

    @Column(unique = true)
    private String password;

    public Client() {
    }

}