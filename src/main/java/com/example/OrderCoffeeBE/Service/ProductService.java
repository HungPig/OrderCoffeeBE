package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Request.PostProductRequest;
import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface ProductService {
    List<products> findAll();
    products findById(int id);
    products createProduct(PostProductRequest product);
    products updateProduct(products product);
    void deleteProduct(products product);
    boolean isNameExist(String name);
}
