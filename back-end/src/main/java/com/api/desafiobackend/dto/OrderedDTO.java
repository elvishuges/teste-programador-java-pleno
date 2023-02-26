package com.api.desafiobackend.dto;

import java.sql.Date;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderedDTO {
    private String description;
    private List<String> products;

}
