package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.categories;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public categories findByIdCate(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public Optional<categories> findByNameCate(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public categories createCate(categories categories) {
        return categoryRepository.save(categories);
    }

    @Override
    public categories updateCate(categories updateCategories) {
        categories existingCategories = categoryRepository.findById(updateCategories.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (updateCategories.getId() > 0) {
            existingCategories.setId(updateCategories.getId());
        }
        if (updateCategories.getName() != null) {
            existingCategories.setName(updateCategories.getName());
        }
        return categoryRepository.save(existingCategories);
    }

    @Override
    public void deleteCate(categories categories) {
        categoryRepository.delete(categories);
    }
}