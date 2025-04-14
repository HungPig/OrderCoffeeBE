package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void CreateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void UpdateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void DeleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
