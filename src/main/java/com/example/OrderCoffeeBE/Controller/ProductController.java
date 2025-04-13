package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.DTO.Request.ProductUpdateRequest;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping
    Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @GetMapping
    List<Product> getProduct() {
        return productService.getProduct();
    }
    @PutMapping("/{productId}")
    Product updateProduct(@PathVariable int id, @RequestBody ProductUpdateRequest request) {
        return productService.UpdateProduct(id, request);
    }
}

