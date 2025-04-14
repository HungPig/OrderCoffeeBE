package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(int id);
    public void CreateProduct(Product product);
    public void UpdateProduct(Product product);
    public void DeleteProduct(int id);
}
