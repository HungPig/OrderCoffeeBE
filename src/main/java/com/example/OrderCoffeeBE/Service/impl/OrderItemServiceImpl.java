package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Model.OrderItem;
import com.example.OrderCoffeeBE.Model.Product;
import com.example.OrderCoffeeBE.Service.OrderItemService;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import com.example.OrderCoffeeBE.repository.OrderItemRepository;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service("OrderItemService")
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem createOrderItem(OrderItem order_item) {
        OrderItem orderItem = orderItemRepository.findById(order_item.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found with ID: " + order_item.getId()));
        Product product  = productRepository.findById(orderItem.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product Id Not Found"));
        return orderItemRepository.save(order_item);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem updateOrder_item) {
        OrderItem currentOrder = orderItemRepository.findById(updateOrder_item.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found with ID: " + updateOrder_item.getId()));
        Product existsProduct  = productRepository.findById(updateOrder_item.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product Id Not Found"));
        currentOrder.setProduct(existsProduct);
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
        orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order item not found with ID: " + id));
        return orderItemRepository.findById(id).get();
    }
}
