package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.DTO.Request.ProductCreationRequest;
import com.example.OrderCoffeeBE.DTO.Request.ProductUpdateRequest;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createdRequest(ProductCreationRequest request) {
        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStatus(request.getStatus());
        product.setDescription(request.getDescription());
        return productRepository.save(product);
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product UpdateProduct(Integer id, @RequestBody ProductUpdateRequest request) {
        Product product = getProductById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStatus(request.getStatus());

        return productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
