package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Convert.UserConvert;
import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.Role;
import com.example.OrderCoffeeBE.Entity.dto.UpdateUserDTO;
import com.example.OrderCoffeeBE.Entity.user;
import com.example.OrderCoffeeBE.Service.UserService;
import com.example.OrderCoffeeBE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public UpdateUserRequest updateUser(int id , UpdateUserDTO user) {

        Optional<user> userOptional = this.userRepository.findById(id);
        if(userOptional.isPresent())
        {
            user currentUser = userOptional.get();
            currentUser.setName(user.getName());
            currentUser.setEmail(user.getEmail());
            currentUser.setAge(user.getAge());
            currentUser.setGender(user.getGender());
            return UserConvert.convertToResUpdateUserRes(this.userRepository.save(currentUser));
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }
    }
}
