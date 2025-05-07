package com.example.OrderCoffeeBE.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer table_id;
    private String status;
    private int total_amount;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
