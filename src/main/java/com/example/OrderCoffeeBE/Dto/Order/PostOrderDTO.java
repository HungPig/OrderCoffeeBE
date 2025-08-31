package com.example.OrderCoffeeBE.Dto.Order;

import com.example.OrderCoffeeBE.Entity.Request.Order.PostOrderItemRequest;
import lombok.Data;

import java.util.List;

@Data
public class PostOrderDTO {
    private int id;
    private int table_id;
    private String status;
    private int totalAmount;
    private List<PostOrderItemRequest> items;
}
