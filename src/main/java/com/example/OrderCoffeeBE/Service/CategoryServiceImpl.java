package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.categories;
import com.example.OrderCoffeeBE.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository _categoryRepository) {
        categoryRepository = _categoryRepository;
    }

    @Override
    public List<categories> getAllCategories() {
        return categoryRepository.findAllByDeFL(0);
    }

    @Override
    public categories findByIdCate(int id) {
        return categoryRepository.findByIdAndDeFLNot(id, 1)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public void createCate(categories categories) {
        categories.setDeFL(0);
        categoryRepository.save(categories);
    }

    @Override
    public void updateCate(categories updateCategories) {
        categories existingCategories = categoryRepository.findById(updateCategories.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (updateCategories.getId() > 0) {
            existingCategories.setId(updateCategories.getId());
        }
        if (updateCategories.getName() != null) {
            existingCategories.setName(updateCategories.getName());
        }
        categoryRepository.save(existingCategories);
    }

    @Override
    public void deleteCate(categories categories) {
        categories.setDeFL(1);
        categoryRepository.delete(categories);
    }
}