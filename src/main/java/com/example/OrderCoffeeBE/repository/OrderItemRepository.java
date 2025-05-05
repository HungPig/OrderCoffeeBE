package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.orders_items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<orders_items, Integer> {

}
