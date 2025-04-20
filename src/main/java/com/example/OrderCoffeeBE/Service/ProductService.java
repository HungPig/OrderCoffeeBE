package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<products> findAll();
    public products findById(int id);
    public Optional<products> findByName(String name);
    public products CreateProduct(products product);
    public products UpdateProduct(products product);
    public products DeleteProduct(products product);
}
