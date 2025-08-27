package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Entity.dto.UpdateUserDTO;
import com.example.OrderCoffeeBE.Entity.user;
import com.example.OrderCoffeeBE.Service.impl.UserServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<user>>> getUser() {
        List<user> userList = userService.getAllUser();
        return ResponseEntity.ok(ApiResponse.success("GET User success", userList));
    }
    @PostMapping
    public ResponseEntity<PostUserRequest> addUser( @RequestBody user user) {
        PostUserRequest newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable int id, @RequestBody UpdateUserDTO user) {
       return ResponseEntity.ok(this.userService.updateUser(id,user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") int id
    ) throws Exception {
        this.userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
