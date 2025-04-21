package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<categories, Integer> {
    List<categories> findAllByDeFL(int deLF);

    Optional<categories> findByIdAndDeFLNot(int id, int deFL);
}
