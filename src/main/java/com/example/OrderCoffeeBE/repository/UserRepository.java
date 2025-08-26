package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Integer> {
}
