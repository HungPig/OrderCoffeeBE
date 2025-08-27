package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.dto.UpdateUserDTO;
import com.example.OrderCoffeeBE.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    PostUserRequest createUser(User user);
    UpdateUserRequest updateUser(int id, UpdateUserDTO user);
    void deleteUser(int id);
}
