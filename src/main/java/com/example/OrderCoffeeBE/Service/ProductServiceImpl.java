package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Category;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product CreateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product UpdateProduct(Product product) {
        if(product.getId() != null)
        {
            findById(product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public Product DeleteProduct(Product product) {
        // Verify product exists before updating
        if (product.getId() != null) {
            findById(product.getId()); // Will throw exception if not found
        }
        return productRepository.save(product);
    }


}
