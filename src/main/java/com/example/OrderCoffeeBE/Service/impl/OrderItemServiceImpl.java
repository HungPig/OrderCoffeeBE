package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.orders_items;
import com.example.OrderCoffeeBE.Service.OrderItemService;
import com.example.OrderCoffeeBE.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service("OrderItemService")
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Override
    public List<orders_items> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public orders_items createOrderItem(orders_items order_item) {
        return orderItemRepository.save(order_item);
    }

    @Override
    public orders_items updateOrderItem(orders_items updateOrder_item) {
        orders_items currentOrder = orderItemRepository.findById(updateOrder_item.getId()).get();
        if (currentOrder != null) {
            currentOrder.setOrder_id(updateOrder_item.getOrder_id());
            currentOrder.setProduct_id(updateOrder_item.getProduct_id());
            currentOrder.setQuantity(updateOrder_item.getQuantity());
            currentOrder.setSubtotal(updateOrder_item.getSubtotal());
            currentOrder.setStatus(updateOrder_item.getStatus());
            currentOrder.setNotes(updateOrder_item.getNotes());
            orderItemRepository.save(currentOrder);
        }
        return updateOrder_item;
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public orders_items findById(int id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + id));
    }
}
