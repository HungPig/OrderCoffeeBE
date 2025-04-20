package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public List<products> findAll() {
        return productRepository.findAll();
    }

    @Override
    public products findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public Optional<products> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public products CreateProduct(products product) {
        return productRepository.save(product);
    }

    @Override
    public products UpdateProduct(products product) {
        return productRepository.save(product);
    }

    @Override
    public products DeleteProduct(products product) {
        return productRepository.save(product);
    }


}
