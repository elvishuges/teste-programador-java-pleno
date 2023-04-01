package com.api.desafiobackend.dto;

import java.sql.Date;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderedDTO {
    private String description;
    private Long number;
    private Long id;
    private List<String> products;

}
