package com.example.OrderCoffeeBE.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreationRequest {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Double status;
    private String categoryName;
}
