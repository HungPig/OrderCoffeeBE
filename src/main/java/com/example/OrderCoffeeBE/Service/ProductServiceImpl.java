package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository _productRepository) {
        productRepository = _productRepository;
    }
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
    public products createProduct(products product) {
        return productRepository.save(product);
    }

    @Override
    public products updateProduct(products updateProduct) {
        products existingProduct = productRepository.findById(updateProduct.getId())
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + updateProduct.getId()));
        if(updateProduct.getName() != null) {
            existingProduct.setName(updateProduct.getName());
        }
        if(updateProduct.getDescription() != null) {
            existingProduct.setDescription(updateProduct.getDescription());
        }
        if(updateProduct.getPrice() > 0) {
            existingProduct.setPrice(updateProduct.getPrice());
        }
        if(updateProduct.getImage() != null) {
            existingProduct.setImage(updateProduct.getImage());
        }
        existingProduct.setStatus(updateProduct.getStatus());
        if(updateProduct.getCategory_id() > 0) {
            existingProduct.setCategory_id(updateProduct.getCategory_id());
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(products product) {
        productRepository.delete(product);
    }
}
