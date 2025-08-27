package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.PostProductRequest;
import com.example.OrderCoffeeBE.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product createProduct(Product Product);
    Product updateProduct(PostProductRequest products, MultipartFile image);
    void deleteProduct(Product product);
}
