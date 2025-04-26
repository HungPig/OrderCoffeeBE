package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.Request.PostProductRequest;
import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import com.example.OrderCoffeeBE.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final String UPLOAD_DIR = "access/products/";

    @Override
    public List<products> findAll() {
        return productRepository.findAll();
    }

    @Override
    public products findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public products createProduct(PostProductRequest request) {
       return this.productRepository.save(request);
    }
    @Override
    public products updateProduct(products updateProduct) {
        products currentProduct = this.findById(updateProduct.getId());
        currentProduct.setName(updateProduct.getName());
        currentProduct.setPrice(updateProduct.getPrice());
        currentProduct.setImage(updateProduct.getImage());
        currentProduct.setDescription(updateProduct.getDescription());
        currentProduct = this.productRepository.save(currentProduct);
        return currentProduct;
    }

    @Override
    public void deleteProduct(products product) {
        productRepository.save(product);
    }

    @Override
    public boolean isNameExist(String name) {
        return this.productRepository.existsByName(name);
    }
}
