package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.Order.PostOrderRequest;
import com.example.OrderCoffeeBE.Entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order createOrder(PostOrderRequest orderDTO);
    Order updateOrder(PostOrderRequest order);
    void sortDeleteOrder(int id);
    Order findById(int id);
}
