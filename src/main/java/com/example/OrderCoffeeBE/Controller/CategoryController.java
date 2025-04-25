package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.categories;
import com.example.OrderCoffeeBE.Service.CategoryService;
import com.example.OrderCoffeeBE.repository.ApiResonse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService _categoryService) {
        categoryService = _categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResonse<List<categories>>> getAllCategories() {
        List<categories> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(ApiResonse.success("Get Category Success", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResonse<categories>> getCategory(@PathVariable int id) {
        try {
            categories category = categoryService.findByIdCate(id);
            return ResponseEntity.ok(ApiResonse.success("Get Category Success", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResonse.error("Get Category failed", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResonse<categories>> createCategory(@RequestBody categories category) {
        try {
            categoryService.createCate(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResonse.success("Add Category Success", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResonse.error("Add Category failed", e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResonse<categories>> updateCategory(@PathVariable int id, @RequestBody categories category) {
        try {
            category.setId(id);
            categoryService.updateCate(category);
            return ResponseEntity.ok(ApiResonse.success("Update Category Success", category));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResonse.error("Category Not Found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResonse.error("Update Category failed", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse<categories>> deleteCategory(@PathVariable int id) {
        try {

            categories category = categoryService.findByIdCate(id);
            categoryService.deleteCate(category);
            return ResponseEntity.ok(ApiResonse.success("Delete Category Success", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResonse.error("Delete Category failed", e.getMessage()));
        }
    }
}