package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.orders_items;

import java.util.List;

public interface OrderItemService {
    List<orders_items> findAll();
    orders_items createOrderItem(orders_items order_item);
    orders_items updateOrderItem(orders_items order_item);
    void deleteOrderItem(int id);
    List<orders_items> findActiveItemsByOrderId(Integer orderId);
}
