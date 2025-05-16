package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.orders_items;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<orders_items, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE orders_items oi SET oi.deleted = true WHERE oi.order.id = :orderId")
    void softDeleteById(Integer orderId);

    @Modifying
    @Transactional
    @Query("UPDATE orders_items oi SET oi.deleted = false WHERE oi.order.id = :orderId")
    void restoreByOrderId(Integer orderId);

    @Query("SELECT oi FROM orders_items oi WHERE oi.deleted = false AND oi.order.id = :orderId")
    List<orders_items> findActiveItemsByOrderId(Integer orderId);
}
