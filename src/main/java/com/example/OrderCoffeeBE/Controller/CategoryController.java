package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Service.impl.CategoryServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable int id) {
        Category fetchCategory = this.categoryService.findByIdCate(id);
        if(fetchCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Category not found " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Get Category Success", fetchCategory));
    }



    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody Category category) {
        //check Name
        boolean isNameExist = this.categoryService.isNameExist(category.getName());
        if (isNameExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Category name already exists"));
        }
        if(category.getName() == null || category.getName().isEmpty()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Category name cannot be empty"));
        }
        Category newCategory = this.categoryService.createCate(category);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Create Category Success", newCategory));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> updateCategory(@PathVariable int id, @RequestBody Category category)  {
        category.setId(id);
        Category hungCategory = this.categoryService.updateCate(category);
        if (hungCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Category not found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Update Category Success", hungCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategory(@PathVariable int id) {
        Category currentCategory = this.categoryService.findByIdCate(id);
        if (currentCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Category not found" + id));
        }
        this.categoryService.deleteCate(id);
        return ResponseEntity.ok(ApiResponse.success("Delete Category Success", currentCategory));
    }
}