package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public  List<Category> getAllCategories();

    public Optional<Category> findByIdCate(int id);

    public Optional<Category> findByNameCate(String name);

    public Category CreateCate(Category category);

    public Category UpdateCate(Category category);

    public Category DeleteCate(int id);
}
