package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.products;

import java.util.List;

public interface ProductService {
    List<products> findAll();
    products findById(int id);
    //     Optional<products> findByName(String name);
    products createProduct(products product);
    products updateProduct(products product);
    void deleteProduct(products product);
}
