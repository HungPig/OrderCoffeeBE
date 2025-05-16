package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<orders, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE orders o SET o.deleted = true WHERE o.id = :orderId")
    void softDeleteById(Integer orderId);

    @Query("SELECT o FROM orders o WHERE o.deleted = false")
    List<orders> findAllActive();
}
