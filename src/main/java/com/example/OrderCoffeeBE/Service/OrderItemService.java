package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Model.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll();
    OrderItem createOrderItem(OrderItem order_item);
    OrderItem updateOrderItem(OrderItem order_item);
    void deleteOrderItem(int id);
    OrderItem findOrderItemById(int id);
}
