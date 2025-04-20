package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(categories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        try {
            Optional<Category> category = categoryService.findByIdCate(id);
            return category.isPresent()
                    ? ResponseEntity.ok(category.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        try {
            Optional<Category> category = categoryService.findByNameCate(name);
            return category.isPresent()
                    ? ResponseEntity.ok(category.get())
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try {
            // Validate input data
            if (category.getName() == null || category.getName().trim().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Tên danh mục không được để trống");
            }
            Optional<Category> existingCategory = categoryService.findByNameCate(category.getName());
            if (existingCategory.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Danh mục với tên '" + category.getName() + "' đã tồn tại");
            }
            Category newCategory = new Category();
            newCategory.setName(category.getName());

            Category createdCategory = categoryService.CreateCate(newCategory);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi server: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        try {
            Optional<Category> existingCategory = categoryService.findByIdCate(id);
            if (!existingCategory.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            category.setId(id);
            Category updatedCategory = categoryService.UpdateCate(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        try {
            Optional<Category> category = categoryService.findByIdCate(id);
            if (!category.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            categoryService.DeleteCate(category.get());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}