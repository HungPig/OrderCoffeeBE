package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Category;

import java.util.List;

public interface CategoryService {
     List<Category> getAllCategories();
     Category findByIdCate(int id);
     Category createCate(Category Category);
     Category updateCate(Category Category);
     void deleteCate(int id);
     boolean isNameExist(String name);
}
