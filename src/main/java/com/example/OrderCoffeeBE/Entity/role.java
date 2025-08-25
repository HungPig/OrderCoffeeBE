package com.example.OrderCoffeeBE.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<user> users;
}
