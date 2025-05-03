package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<categories, Integer> { ;
    boolean existsByName(String name);
    @Query("SELECT c FROM categories c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<categories> findByName(@Param("keyword") String name);
}
