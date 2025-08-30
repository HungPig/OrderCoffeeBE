package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Service.CategoryService;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    @Override
    public Category createCate(Category Category) {
        if(Category == null)
        {
            throw new ResourceNotFoundException("Category Is Required");
        }
        var isNameExist = categoryRepository.findByName(Category.getName());
        if (isNameExist != null) {
            throw new ResourceNotFoundException("Category " + Category.getName() + " already exists, please use another category");
        }
        if(Category.getName() == null || Category.getName().isEmpty()) {
            throw new ResourceNotFoundException("User is not empty");
        }
        return categoryRepository.save(Category);
    }

    @Override
    public Category updateCate(Category updateCategory) {
        Category currentCate = this.findByIdCate(updateCategory.getId());
        if (currentCate == null) {
             throw new ResourceNotFoundException("Category is required");
        }
        var isNameExist = categoryRepository.findByName(updateCategory.getName());
        if (isNameExist != null) {
            throw new ResourceNotFoundException("Category " + updateCategory.getName() + " already exists, please use another category");
        }
        //update cate
        currentCate.setName(updateCategory.getName());
        //save cate
        return this.categoryRepository.save(currentCate);
    }

    @Override
    public void deleteCate(int id) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found");
        }
        this.categoryRepository.deleteById(id);
    }
}