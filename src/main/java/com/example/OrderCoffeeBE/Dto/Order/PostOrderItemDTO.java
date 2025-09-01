package com.example.OrderCoffeeBE.Dto.Order;

import lombok.Data;

@Data
public class PostOrderItemDTO {
    private Integer product_id;
    private int quantity;
    private int subtotal;
    private Integer status;
    private String notes;
}
