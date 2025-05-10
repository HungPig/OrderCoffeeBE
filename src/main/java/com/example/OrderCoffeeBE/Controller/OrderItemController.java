package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.orders_items;
import com.example.OrderCoffeeBE.Service.impl.OrderItemServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderItemController {
    private final OrderItemServiceImpl OrderItemService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<orders_items>>> getAllOrderItems() {
        List<orders_items> orders_items = OrderItemService.findAll();
        if(orders_items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("No  OrderItem found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Get  OrderItem Success", orders_items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<orders_items>> getOrdersById(@PathVariable int id) {
        orders_items fetchOrders = this.OrderItemService.findById(id);
        if(fetchOrders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("orders not found " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Get orders Success", fetchOrders));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<orders_items>> createOrders(@RequestBody orders_items orders) {
        orders_items neworders = this.OrderItemService.createOrderItem(orders);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Create orders Success", neworders));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<orders_items>> updateOrders(@PathVariable int id, @RequestBody orders_items orders)  {
        orders.setId(id);
        orders_items hungOrders = this.OrderItemService.updateOrderItem(orders);
        if (hungOrders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("orders not found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Update orders Success", hungOrders));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<orders_items>> deleteorders(@PathVariable int id) {
        orders_items currentOrders = this.OrderItemService.findById(id);
        if (currentOrders == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("orders not found" + id));
        }
        this.OrderItemService.deleteOrderItem(id);
        return ResponseEntity.ok(ApiResponse.success("Delete orders Success", currentOrders));
    }
}
