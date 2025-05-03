package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.categories;
import com.example.OrderCoffeeBE.Entity.products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<products, Integer> { ;
    boolean existsByName(String name);
    @Query("SELECT c FROM products c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<products> findByName(@Param("keyword") String name);
}
