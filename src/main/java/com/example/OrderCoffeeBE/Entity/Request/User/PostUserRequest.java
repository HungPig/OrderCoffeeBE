package com.example.OrderCoffeeBE.Entity.Request.User;

import com.example.OrderCoffeeBE.Util.Contant.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUserRequest {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private GenderEnum gender;
    private RoleUser role;
}
