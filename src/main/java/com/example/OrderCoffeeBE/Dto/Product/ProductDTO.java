package com.example.OrderCoffeeBE.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private Integer price;
    private Integer status;
    private Integer category_id;
}
