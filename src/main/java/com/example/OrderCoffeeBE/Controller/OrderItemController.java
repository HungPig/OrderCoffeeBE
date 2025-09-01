package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Model.OrderItem;
import com.example.OrderCoffeeBE.Service.impl.OrderItemServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orderItems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemServiceImpl orderItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderItem>>> getAllOrderItems() {
        List<OrderItem> items = orderItemService.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("No order items found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Fetched all order items successfully", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItem>> getOrderItemById(@PathVariable int id) {
        try {
            OrderItem orderItem = orderItemService.findOrderItemById(id); // Tìm một item cụ thể
            return ResponseEntity.ok(ApiResponse.success("Fetched order item successfully",orderItem));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Order item not found with ID: " + id));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderItem>> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem newOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order item created successfully", newOrderItem));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderItem>> updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        orderItem.setId(id);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        if (updatedOrderItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Order item not found with ID: " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Order item updated successfully", updatedOrderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrderItem(@PathVariable int id) {
        try {
            orderItemService.deleteOrderItem(id);
            return ResponseEntity.ok(ApiResponse.success("Order item deleted successfully", "Order item ID: " + id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Order item not found with ID: " + id));
        }
    }
}