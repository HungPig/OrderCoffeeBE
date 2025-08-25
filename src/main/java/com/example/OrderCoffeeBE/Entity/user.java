package com.example.OrderCoffeeBE.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "age", length = 3)
    private Integer age;
    @Column(name = "gender")
    private Integer gender;
    private boolean isActive = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private role role;
}
