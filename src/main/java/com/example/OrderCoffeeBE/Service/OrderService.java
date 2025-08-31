package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Dto.Order.PostOrderDTO;
import com.example.OrderCoffeeBE.Entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order createOrder(PostOrderDTO orderDTO);
    Order updateOrder(PostOrderDTO order);
    void sortDeleteOrder(int id);
    Order findById(int id);
}
