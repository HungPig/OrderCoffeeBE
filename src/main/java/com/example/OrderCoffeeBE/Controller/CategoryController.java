package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Service.impl.CategoryServiceImpl;
import com.example.OrderCoffeeBE.Util.Anotation.ApiMessage;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    @ApiMessage("Fetch All Categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @ApiMessage("Fetch By Id User")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id) {
        Category category = this.categoryService.findByIdCate(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @ApiMessage("Create Categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) throws ResourceNotFoundException {
        Category newCategory = this.categoryService.createCate(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PatchMapping("/{id}")
    @ApiMessage("Update Categories")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category)  throws Exception{
        category.setId(id);
        Category hungCategory = this.categoryService.updateCate(category);
        return ResponseEntity.status(HttpStatus.OK).body(hungCategory);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete Categories")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id){
        this.categoryService.deleteCate(id);
        return ResponseEntity.ok(null);
    }
}