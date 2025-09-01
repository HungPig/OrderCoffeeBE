package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Model.Role;
import com.example.OrderCoffeeBE.Service.RoleService;
import com.example.OrderCoffeeBE.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role fetchRoleById(int id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if(role.isPresent()) {
            return role.get();
        } else {
            return null;
        }
     }
}
