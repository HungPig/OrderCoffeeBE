package com.example.OrderCoffeeBE.Dto.Order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostOrderItemDTO {
    @JsonProperty("product_id")
    private int product_id;
    private int quantity;
    private int subtotal;
    private Integer status;
    private String notes;
}
