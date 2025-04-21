package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<products, Integer> {
    List<products> findAllByDeLF(int deLF);
    Optional<products> findByIdAndDeLFNot(int id, int deLF);
}
