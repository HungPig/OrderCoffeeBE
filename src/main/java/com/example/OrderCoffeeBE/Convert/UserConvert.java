package com.example.OrderCoffeeBE.Convert;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.Role;
import com.example.OrderCoffeeBE.Entity.user;


public class UserConvert {
    public static PostUserRequest convertToResCreatedUserRes(user user){
        PostUserRequest res = new PostUserRequest();
        Role role = new Role();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setAge(user.getAge());
        res.setGender(user.getGender());
        if(user.getRole() != null){
            role.setId(user.getRole().getId());
            role.setDescription(user.getRole().getDescription());
        }
        return res;
    }

    public static UpdateUserRequest convertToResUpdateUserRes(user user)
    {
        UpdateUserRequest res = new UpdateUserRequest();
        Role role = new Role();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setAge(user.getAge());
        res.setGender(user.getGender());
        if(user.getRole() != null){
            role.setId(user.getRole().getId());
            role.setDescription(user.getRole().getDescription());
        }
        return res;
    }
}
