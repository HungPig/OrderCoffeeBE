package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Dto.User.PostUserDTO;
import com.example.OrderCoffeeBE.Dto.User.UpdateUserDTO;
import com.example.OrderCoffeeBE.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    PostUserDTO createUser(User user);
    UpdateUserDTO updateUser(int id, UpdateUserDTO user);
    User findById(int id);
    void deleteUser(int id);
}
