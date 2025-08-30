package com.example.OrderCoffeeBE.Dto;

import com.example.OrderCoffeeBE.Entity.Role;
import com.example.OrderCoffeeBE.Util.Contant.GenderEnum;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private GenderEnum gender;
    private Role role;
}
