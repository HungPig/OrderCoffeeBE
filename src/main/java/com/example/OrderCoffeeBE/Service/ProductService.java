package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public Optional<Product> findByName(String name);
    public Product CreateProduct(Product product);
    public Product UpdateProduct(Product product);
    public Product DeleteProduct(Product product);
}
