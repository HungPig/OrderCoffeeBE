package com.example.OrderCoffeeBE.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
