package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Request.User.PostUserRequest;
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
//    @PatchMapping()
//    public ResponseEntity<ApiResponse<tables>> updateTable(@PathVariable int id, @RequestBody tables table) {
//        table.setId(id);
//        tables hungTable = this.tableService.updateTable(table);
//        if (hungTable == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponse.error("Category not found"));
//        }
//        return ResponseEntity.ok(ApiResponse.success("Update Category Success", hungTable));
//    }
//    @DeleteMapping()
//    public ResponseEntity<ApiResponse<tables>> deleteTable(@RequestParam int id) {
//        tables currentTables = this.tableService.findById(id);
//        if (currentTables != null) {
//            this.tableService.deleteTable(id);
//            return ResponseEntity.ok(ApiResponse.success("Delete Product success", null));
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponse.error("Product not found"));
//        }
//    }
}
