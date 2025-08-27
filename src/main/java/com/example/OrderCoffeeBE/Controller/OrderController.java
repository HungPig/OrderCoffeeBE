package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Request.Order.PostOrderRequest;
import com.example.OrderCoffeeBE.Entity.Order;
import com.example.OrderCoffeeBE.Service.impl.OrderServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrder() {
        List<Order> Order = orderService.findAll();
        if (Order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("No Order found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Get Order Success", Order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable int id) {
        Order fetchCategory = this.orderService.findById(id);
        if (fetchCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Order not found " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Get Order Success", fetchCategory));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody PostOrderRequest order) {
        Order newOrder = this.orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Create Orders Success", newOrder));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable int id, @RequestBody PostOrderRequest order) {
        order.setId(id);
        Order hungOrder = this.orderService.updateOrder(order);
        if (hungOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Order not found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Update Order Success", hungOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> deleteOrder(@PathVariable int id) {
        Order currentOrder = this.orderService.findById(id);
        if (currentOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Order not found" + id));
        }
        this.orderService.sortDeleteOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Delete Order Success", currentOrder));
    }
}
