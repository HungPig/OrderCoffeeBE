package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> { ;
    boolean existsByName(String name);
}
