package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<orders, Integer> {
}
