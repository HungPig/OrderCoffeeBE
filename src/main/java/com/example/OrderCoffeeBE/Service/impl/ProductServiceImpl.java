package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@RequiredArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<products> findAll() {
        return productRepository.findAllByDelF(0);
    }

    @Override
    public products findById(int id) {
        return productRepository.findByIdAndDelFNot(id, 1)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    @Override
    public products createProduct(products product) {
        product.setDelF(0);
        return productRepository.save(product);
    }

    @Override
    public products updateProduct(products updateProduct) {
        products currentProduct = this.findById(updateProduct.getId());
        if(currentProduct != null) {
            currentProduct.setName(updateProduct.getName());
            currentProduct.setPrice(updateProduct.getPrice());
            currentProduct.setImage(updateProduct.getImage());
            currentProduct.setDescription(updateProduct.getDescription());
            currentProduct.setCategoryId(updateProduct.getCategoryId());
            currentProduct.setDelF(updateProduct.getDelF());
            currentProduct = this.productRepository.save(currentProduct);
        }
        return currentProduct;
    }

    @Override
    public void deleteProduct(products product) {
        product.setDelF(1);
        productRepository.save(product);
    }

    @Override
    public boolean isNameExist(String name) {
        return this.productRepository.existsByName(name);
    }
}
