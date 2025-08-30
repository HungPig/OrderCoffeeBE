package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
import com.example.OrderCoffeeBE.Entity.Request.User.UpdateUserRequest;
import com.example.OrderCoffeeBE.Dto.UpdateUserDTO;
import com.example.OrderCoffeeBE.Entity.User;
import com.example.OrderCoffeeBE.Service.impl.UserServiceImpl;
import com.example.OrderCoffeeBE.Util.Anotation.ApiMessage;
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
    @ApiMessage("fetch all User")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUser());
    }
    @PostMapping
    @ApiMessage("Create User")
    public ResponseEntity<PostUserRequest> addUser( @RequestBody User user) {
        PostUserRequest newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PutMapping("/{id}")
    @ApiMessage("Update User")
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable int id, @RequestBody UpdateUserDTO user) {
       return ResponseEntity.ok(this.userService.updateUser(id,user));
    }
    @DeleteMapping("/{id}")
    @ApiMessage("Delete User")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") int id
    ) throws Exception {
        this.userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
