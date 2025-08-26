package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.user;

import java.util.List;

public interface UserService {
    List<user> getAllUser();
    PostUserRequest createUser(user user);
    UpdateUserRequest updateUser(long id, user user);
    void deleteUser(int id);
}
