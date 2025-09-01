package com.example.OrderCoffeeBE.Dto.User;

import com.example.OrderCoffeeBE.Model.Role;
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
