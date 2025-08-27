package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Tables, Integer> {
}
