package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Convert.UserConvert;
import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.Role;
import com.example.OrderCoffeeBE.Entity.dto.UpdateUserDTO;
import com.example.OrderCoffeeBE.Entity.User;
import com.example.OrderCoffeeBE.Service.RoleService;
import com.example.OrderCoffeeBE.Service.UserService;
import com.example.OrderCoffeeBE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("UserService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public PostUserRequest createUser(User user) {
        if(user.getRole() != null){
            Role role = this.roleService.fetchRoleById(user.getRole().getId());
            user.setRole(role);
        }
        return UserConvert.convertToResCreatedUserRes(this.userRepository.save(user));
    }

    @Override
    public UpdateUserRequest updateUser(int id , UpdateUserDTO user) {

        Optional<User> userOptional = this.userRepository.findById(id);
        if(userOptional.isPresent())
        {
            User currentUser = userOptional.get();
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
