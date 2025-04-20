package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByIdCate(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByNameCate(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category CreateCate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category UpdateCate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category DeleteCate(Category category) {
        if (category != null) {
            Optional<Category> existingCategory = categoryRepository.findById(category.getId());
            if (existingCategory.isPresent()) {
                categoryRepository.deleteById(category.getId());
                return existingCategory.get();
            }
        }
        return null;
    }
}