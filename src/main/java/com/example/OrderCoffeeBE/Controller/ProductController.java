package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.repository.ApiResonse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService _productService) {
        productService = _productService;
    }

    @GetMapping
    public ResponseEntity<ApiResonse<List<products>>> getAllProducts() {
        List<products> products = productService.findAll();
        return ResponseEntity.ok(ApiResonse.success("GET Products success", products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResonse<products>> getProductById(@PathVariable Integer id) {
        try {
            products product = productService.findById(id);
            return ResponseEntity.ok(ApiResonse.success("GET Product success", product));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ApiResonse<products>> createProduct(@RequestBody products product) {
        try {
            products createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResonse.success("Create Product success", createdProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResonse.error("Create Product failed", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResonse<products>> updateProduct(@PathVariable int id, @RequestBody products product) {
        try {
            product.setId(id);
            products updatedProduct = productService.updateProduct(product);
            return ResponseEntity.ok(ApiResonse.success("Update Product success", updatedProduct));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResonse.error("Product not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResonse.error("Update Product failed", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResonse<Void>> deleteProduct(@PathVariable int id) {
        try {
            products product = productService.findById(id);
            productService.deleteProduct(product);
            return ResponseEntity.ok().body(ApiResonse.success("Delete Product success", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResonse.error("Delete Product failed", e.getMessage()));
        }
    }
}