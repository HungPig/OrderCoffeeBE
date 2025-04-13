package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
