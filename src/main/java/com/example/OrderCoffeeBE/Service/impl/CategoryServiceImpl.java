package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.categories;
import com.example.OrderCoffeeBE.Service.CategoryService;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<categories> getAllCategories() {
        return categoryRepository.findAllByDelF(0);
    }

    @Override
    public categories findByIdCate(int id) {
        Optional<categories> categoryOptional = categoryRepository.findByIdAndDelFNot(id, 1);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new NoSuchElementException("Category not found with id: " + id);
        }
    }

    @Override
    public categories createCate(categories categories) {
        categories.setDelF(0);
        return categoryRepository.save(categories);
    }

    @Override
    public categories updateCate(categories updateCategories) {
        categories currentCategories = this.findByIdCate(updateCategories.getId());
        currentCategories.setName(updateCategories.getName());
        return this.categoryRepository.save(currentCategories);
    }

    @Override
    public categories deleteCate(categories categories) {
        categories.setDelF(1);
        return this.categoryRepository.save(categories);
    }

    @Override
    public boolean isNameExist(String name) {
        return this.categoryRepository.existsByName(name);
    }
}