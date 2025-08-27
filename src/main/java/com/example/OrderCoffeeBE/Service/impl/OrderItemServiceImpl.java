package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.OrderItem;
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
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem createOrderItem(OrderItem order_item) {
        return orderItemRepository.save(order_item);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem updateOrder_item) {
        OrderItem currentOrder = orderItemRepository.findById(updateOrder_item.getId())
                .orElseThrow(() -> new NoSuchElementException("Order item not found with ID: " + updateOrder_item.getId()));

        if (updateOrder_item.getProduct_id() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        currentOrder.setProduct_id(updateOrder_item.getProduct_id());
        currentOrder.setQuantity(updateOrder_item.getQuantity());
        currentOrder.setSubtotal(updateOrder_item.getSubtotal());
        currentOrder.setNotes(updateOrder_item.getNotes());

        return orderItemRepository.save(currentOrder);
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem findOrderItemById(int id) {
        orderItemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order item not found with ID: " + id));
        return orderItemRepository.findById(id).get();
    }
}
