package com.example.OrderCoffeeBE.repository;

import com.example.OrderCoffeeBE.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
