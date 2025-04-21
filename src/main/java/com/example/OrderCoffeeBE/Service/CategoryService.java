package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.categories;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public  List<categories> getAllCategories();

    public categories findByIdCate(int id);

    public Optional<categories> findByNameCate(String name);

    public categories createCate(categories categories);

    public categories updateCate(categories categories);

    public void deleteCate(categories categories);
}
