package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Dto.Order.OrderDTO;
import com.example.OrderCoffeeBE.Dto.Order.PostOrderDTO;
import com.example.OrderCoffeeBE.Model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order createOrder(PostOrderDTO orderDTO);
    Order updateOrder(int id, OrderDTO orderDTO);
    void sortDeleteOrder(int id);
    Order findById(int id);

}
