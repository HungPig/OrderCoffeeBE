package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.Order.PostOrderRequest;
import com.example.OrderCoffeeBE.Entity.orders;

import java.util.List;

public interface OrderService {
    List<orders> findAll();
    orders createOrder(PostOrderRequest orderDTO);
    orders updateOrder(PostOrderRequest order);
    void sortDeleteOrder(int id);
    orders findById(int id);
}
