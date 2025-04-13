package com.example.OrderCoffeeBE.DTO.Request;

import com.example.OrderCoffeeBE.Entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    private String name;
    private String description;
    private Double price;
    private Double status;

    public ProductUpdateRequest(Double status, Double price, String description, String name) {
        this.status = status;
        this.price = price;
        this.description = description;
        this.name = name;
    }
}
