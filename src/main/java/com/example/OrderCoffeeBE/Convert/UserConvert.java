package com.example.OrderCoffeeBE.Convert;

import com.example.OrderCoffeeBE.Dto.User.PostUserDTO;
import com.example.OrderCoffeeBE.Dto.User.RoleUserDTO;
import com.example.OrderCoffeeBE.Dto.User.UpdateUserDTO;
import com.example.OrderCoffeeBE.Model.Role;
import com.example.OrderCoffeeBE.Model.User;


public class UserConvert {
    public static PostUserDTO convertToResCreatedUserRes(User user){
        PostUserDTO res = new PostUserDTO();
        RoleUserDTO roleUserDTO = new RoleUserDTO();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        res.setAge(user.getAge());
        res.setGender(user.getGender());
        if(user.getRole() != null){
            roleUserDTO.setId(user.getRole().getId());
            roleUserDTO.setDescription(user.getRole().getDescription());
            res.setRole(roleUserDTO);
        }
        return res;
    }

    public static UpdateUserDTO convertToResUpdateUserRes(User user)
    {
        UpdateUserDTO res = new UpdateUserDTO();
        Role role = new Role();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
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
