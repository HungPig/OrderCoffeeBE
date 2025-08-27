package com.example.OrderCoffeeBE.Convert;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.RoleUser;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.Role;
import com.example.OrderCoffeeBE.Entity.User;


public class UserConvert {
    public static PostUserRequest convertToResCreatedUserRes(User user){
        PostUserRequest res = new PostUserRequest();
        RoleUser roleUser = new RoleUser();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setAge(user.getAge());
        res.setGender(user.getGender());
        if(user.getRole() != null){
            roleUser.setId(user.getRole().getId());
            roleUser.setDescription(user.getRole().getDescription());
            res.setRole(roleUser);
        }
        return res;
    }

    public static UpdateUserRequest convertToResUpdateUserRes(User user)
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
