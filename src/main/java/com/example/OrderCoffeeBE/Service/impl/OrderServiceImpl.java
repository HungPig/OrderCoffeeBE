package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Dto.Order.OrderDTO;
import com.example.OrderCoffeeBE.Dto.Order.PostOrderDTO;
import com.example.OrderCoffeeBE.Model.Product;
import com.example.OrderCoffeeBE.Dto.Order.PostOrderItemDTO;
import com.example.OrderCoffeeBE.Model.Order;
import com.example.OrderCoffeeBE.Model.OrderItem;
import com.example.OrderCoffeeBE.Service.OrderService;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import com.example.OrderCoffeeBE.repository.OrderItemRepository;
import com.example.OrderCoffeeBE.repository.OrdersRepository;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import com.example.OrderCoffeeBE.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    @Override
    public List<Order> findAll() {
        return ordersRepository.findAllNotDeleted();
    }

    @Override
    public Order createOrder(PostOrderDTO orderDTO) {
        // Validate input
        if(orderDTO == null)
        {
            throw new ResourceNotFoundException("Order Is Required");
        }
        if (orderDTO.getTable_id() <= 0)
        {
            throw new ResourceNotFoundException("Invalid table ID");
        }
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new ResourceNotFoundException("Order must contain at least one item.");
        }
        for (PostOrderItemDTO item : orderDTO.getItems()) {
            if (item.getProduct_id() <= 0) {
                throw new ResourceNotFoundException("Invalid product ID: " + item.getProduct_id());
            }
            if (item.getQuantity() <= 0) {
                throw new ResourceNotFoundException("Invalid quantity: " + item.getQuantity());
            }
            if (item.getSubtotal() < 0) {
                throw new ResourceNotFoundException("Invalid subtotal: " + item.getSubtotal());
            }
        }
        Set<Integer> requestedProductIds = orderDTO.getItems().stream()
                .map(PostOrderItemDTO::getProduct_id)
                .collect(Collectors.toSet());

        List<Product> existingProducts = productRepository.findAllById(requestedProductIds);
        Set<Integer> existingProductIds = existingProducts.stream()
                .map(Product::getId)
                .collect(Collectors.toSet());

        Set<Integer> missingProductIds = requestedProductIds.stream()
                .filter(id -> !existingProductIds.contains(id))
                .collect(Collectors.toSet());

        if (!missingProductIds.isEmpty()) {
            throw new ResourceNotFoundException("Products not found with IDs: " + missingProductIds);
        }
        Order order = new Order();
        order.setTable_id(orderDTO.getTable_id());
        order.setStatus("Pending");
        order.setDeleted(0);
        int calculatedTotal = orderDTO.getItems().stream()
                .mapToInt(PostOrderItemDTO::getSubtotal)
                .sum();
        order.setTotal_amount(calculatedTotal);
        Order savedOrder = ordersRepository.save(order);
        List<OrderItem> orderItemsList = new ArrayList<>();
        for (PostOrderItemDTO itemDTO : orderDTO.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(savedOrder);
            item.setProduct_id(itemDTO.getProduct_id());
            item.setQuantity(itemDTO.getQuantity());
            item.setSubtotal(itemDTO.getSubtotal());
            item.setNotes(itemDTO.getNotes());
            orderItemsList.add(item);
        }
        // Lưu các items của đơn hàng
        orderItemRepository.saveAll(orderItemsList);
        // Gán items vào đơn hàng và trả về
        savedOrder.setItems(orderItemsList);
        return savedOrder;
    }
    @Override
    public Order updateOrder(OrderDTO orderDTO) {
        // Validate input
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order data is required");
        }
        if (orderDTO.getId() <= 0 || orderDTO.getId() <= 0) {
            throw new IllegalArgumentException("Valid Order ID is required");
        }
        // Find existing order
        Order currentOrder = ordersRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderDTO.getId()));

        // Update table_id if provided and valid
        if (orderDTO.getTable_id() != null && orderDTO.getTable_id() > 0) {
            // Check if table exists
            if (!tableRepository.existsById(orderDTO.getTable_id())) {
                throw new ResourceNotFoundException("Table not found with ID: " + orderDTO.getTable_id());
            }
            currentOrder.setTable_id(orderDTO.getTable_id());
        }

        // Update status if provided
        if (orderDTO.getStatus() != null && !orderDTO.getStatus().trim().isEmpty()) {
            currentOrder.setStatus(orderDTO.getStatus());
        }

        // Update items if provided
        if (orderDTO.getItems() != null && !orderDTO.getItems().isEmpty()) {
            // Delete existing items for this order
            orderItemRepository.deleteById(currentOrder.getId());

            // Create new items
            List<OrderItem> newOrderItems = new ArrayList<>();
            int newTotal = 0;

            for (PostOrderItemDTO itemDTO : orderDTO.getItems()) {
                if (!productRepository.existsById(itemDTO.getProduct_id())) {
                    throw new ResourceNotFoundException("Product not found with ID: " + itemDTO.getProduct_id());
                }

                // Validate item data
                if (itemDTO.getQuantity() <= 0) {
                    throw new IllegalArgumentException("Invalid quantity: " + itemDTO.getQuantity());
                }
                if (itemDTO.getSubtotal() < 0) {
                    throw new IllegalArgumentException("Invalid subtotal: " + itemDTO.getSubtotal());
                }

                // Create new OrderItem
                OrderItem item = new OrderItem();
                item.setOrder(currentOrder);
                item.setProduct_id(itemDTO.getProduct_id());
                item.setQuantity(itemDTO.getQuantity());
                item.setSubtotal(itemDTO.getSubtotal());
                item.setNotes(itemDTO.getNotes());

                newOrderItems.add(item);
                newTotal += itemDTO.getSubtotal();
            }

            // Save new items
            orderItemRepository.saveAll(newOrderItems);

            // Update order total
            currentOrder.setTotal_amount(newTotal);
            currentOrder.setItems(newOrderItems);
        }
        // Update metadata
        return ordersRepository.save(currentOrder);
    }

    @Override
    public void sortDeleteOrder(int id) {
        Order order = findById(id);
        ordersRepository.softDeleteById(id);
    }

    @Override
    public Order findById(int id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }
}
