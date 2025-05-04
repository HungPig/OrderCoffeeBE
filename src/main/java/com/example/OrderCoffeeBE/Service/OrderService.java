package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.orders;
import com.example.OrderCoffeeBE.Entity.tables;

import java.util.List;

public interface OrderService {
    List<orders> findAll();
    orders createOrder(orders order);
    orders updateOrder(orders order);
    void deleteOrder(orders order);
    orders findById(int id);
}
