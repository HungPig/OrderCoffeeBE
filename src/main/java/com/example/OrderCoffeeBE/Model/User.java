package com.example.OrderCoffeeBE.Model;

import com.example.OrderCoffeeBE.Util.Contant.GenderEnum;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
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
    @Enumerated(EnumType.ORDINAL) // MALE = 0 , FEMALE 1
    private GenderEnum gender;
    private boolean isActive = true;
    @ManyToOne()
    @JoinColumn(name = "role_id", nullable = false)
    private Role Role;
}
