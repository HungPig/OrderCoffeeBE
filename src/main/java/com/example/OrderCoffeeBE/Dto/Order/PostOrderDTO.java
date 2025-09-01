package com.example.OrderCoffeeBE.Dto.Order;

import lombok.Data;

import java.util.List;

@Data
public class PostOrderDTO {
    private int table_id;
    private String status;
    private int totalAmount;
    private List<PostOrderItemDTO> items;
}
