package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
