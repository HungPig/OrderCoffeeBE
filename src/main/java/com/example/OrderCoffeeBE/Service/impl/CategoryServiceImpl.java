package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Service.CategoryService;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByIdCate(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category createCate(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public Category updateCate(Category updateCategory) {
        Category currentCate = this.findByIdCate(updateCategory.getId());
        if (currentCate == null) {
            return null;
        }
        currentCate.setName(updateCategory.getName());
        return this.categoryRepository.save(currentCate);
    }

    @Override
    public void deleteCate(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public boolean isNameExist(String name) {
        return this.categoryRepository.existsByName(name);
    }
}