package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.categories;

import java.util.List;

public interface CategoryService {
     List<categories> getAllCategories();
     categories findByIdCate(int id);
//     Optional<categories> findByNameCate(String name);
     void createCate(categories categories);
     void updateCate(categories categories);
     void deleteCate(categories categories);
}
