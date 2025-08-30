package com.example.OrderCoffeeBE.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PostProductDTO {
    private String name;
    private String description;
    private Integer price;
    private Integer status;
    private Integer category_id;
}
