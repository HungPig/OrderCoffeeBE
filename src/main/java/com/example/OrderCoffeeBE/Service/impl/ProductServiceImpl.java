package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.Request.PostProductRequest;
import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.OrderCoffeeBE.Controller.ProductController.uploadDirectory;

@RequiredArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

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
    public products createProduct(products product) {
        return productRepository.save(product);
    }

    @Override
    public products updateProduct(products updateProduct) {
        Optional<products> productOptional = productRepository.findById(updateProduct.getId());

        if (productOptional.isPresent()) {
            products currentProduct = productOptional.get();

            if (updateProduct.getName() != null) {
                currentProduct.setName(updateProduct.getName());
            }
            if (updateProduct.getDescription() != null) {
                currentProduct.setDescription(updateProduct.getDescription());
            }
            if (updateProduct.getPrice() != null) {
                currentProduct.setPrice(updateProduct.getPrice());
            }
            if (updateProduct.getStatus() != null) {
                currentProduct.setStatus(updateProduct.getStatus());
            }
            if (updateProduct.getCategory_id() != null) {
                currentProduct.setCategory_id(updateProduct.getCategory_id());
            }
            return this.productRepository.save(currentProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(products product) {
        productRepository.delete(product);
    }

    @Override
    public boolean isNameExist(String name) {
        return this.productRepository.existsByName(name);
    }
}
