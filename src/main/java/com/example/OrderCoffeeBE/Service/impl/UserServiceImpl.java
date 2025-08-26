package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Convert.UserConvert;
import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.user;
import com.example.OrderCoffeeBE.Service.UserService;
import com.example.OrderCoffeeBE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service("UserService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<user> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public PostUserRequest createUser(user user) {
        return UserConvert.convertToResCreatedUserRes(this.userRepository.save(user));
    }

    @Override
    public UpdateUserRequest updateUser(long id ,user user) {

        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
