package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Model.OrderItem;
import com.example.OrderCoffeeBE.Service.impl.OrderItemServiceImpl;
import com.example.OrderCoffeeBE.Util.Anotation.ApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemServiceImpl orderItemService;

    @GetMapping
    @ApiMessage("Fetch OrderItem")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        return ResponseEntity.status(HttpStatus.OK).body(this.orderItemService.findAll());
    }

    @GetMapping("/{id}")
    @ApiMessage("Fetch By Id OrderItem")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.orderItemService.findOrderItemById(id));
    }

    @PostMapping
    @ApiMessage("Create a OrderItem")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem newOrder = this.orderItemService.createOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @PatchMapping("/{id}")
    @ApiMessage("update OrderItem")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        orderItem.setId(id);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete a OrderItem")
    public ResponseEntity<String> deleteOrderItem(@PathVariable int id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok(null);
    }
}