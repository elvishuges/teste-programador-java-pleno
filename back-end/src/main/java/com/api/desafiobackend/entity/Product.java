package com.api.desafiobackend.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private long code;

    @PrePersist
    public void gerarCodigo() {
        Random random = new Random();
        long randomCode = random.nextInt(100000);
        this.code = randomCode;
    }

    private String description;
    private String unit;
    private double value;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name = "ordered_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "ordered_id"))
    @JsonIgnore
    private List<Ordered> ordered;

    public Product() {
    }

}