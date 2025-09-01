package com.example.OrderCoffeeBE.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "Role", fetch = FetchType.LAZY)
    @JsonIgnore
    List<User> users;
}
