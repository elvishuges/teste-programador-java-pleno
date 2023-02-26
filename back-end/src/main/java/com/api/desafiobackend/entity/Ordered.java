package com.api.desafiobackend.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "ordereds")
@Setter
@Getter
public class Ordered implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    private Long number;

    @CreationTimestamp
    private Date date;

    private String description;

    @ManyToMany
    @JoinTable(name = "ordered_product", joinColumns = @JoinColumn(name = "ordered_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonManagedReference
    private List<Product> products;

    // Constructor
    public Ordered(String description, List<Product> products) {
        this.description = description;
        this.products = products;
    }

    public Ordered() {
    }

    // Getters and Setters

}
