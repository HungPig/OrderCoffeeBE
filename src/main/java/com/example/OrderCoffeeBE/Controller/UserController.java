package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Dto.User.PostUserDTO;
import com.example.OrderCoffeeBE.Dto.User.UpdateUserDTO;
import com.example.OrderCoffeeBE.Model.User;
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
    public ResponseEntity<PostUserDTO> addUser(@RequestBody User user) {
        PostUserDTO newUser = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    @ApiMessage("Update User")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable int id, @RequestBody UpdateUserDTO user) {
        return ResponseEntity.ok(this.userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete User")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") int id) {
        this.userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
