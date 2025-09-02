package com.example.OrderCoffeeBE.Dto.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("category_id")
    private Integer category_id;
}
