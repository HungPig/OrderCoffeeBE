package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<products, Integer> {
    Optional<products> findByName(String name);
}
