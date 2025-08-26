package com.example.OrderCoffeeBE.Entity.Request.User;

import com.example.OrderCoffeeBE.Util.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private GenderEnum gender;
    private Role role;
}
