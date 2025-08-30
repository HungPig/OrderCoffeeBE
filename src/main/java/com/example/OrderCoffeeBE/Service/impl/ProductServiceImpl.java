package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Dto.Product.PostProductDTO;
import com.example.OrderCoffeeBE.Dto.Product.ProductDTO;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.Service.ProductService;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public static String uploadDirectory = System.getProperty("user.dir") + "/access/products";
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
    public Product createProduct(PostProductDTO request ,MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        Path path = Paths.get(uploadDirectory, originalFilename);
        Files.write(path, image.getBytes());
        // 2. Map DTO -> Entity
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStatus(request.getStatus());
        product.setCategory_id(request.getCategory_id());
        product.setImage(originalFilename);
        // 3. Save DB
        return productRepository.save(product);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO ProductDTO, MultipartFile image) throws IOException {
        if(ProductDTO == null)
        {
            throw new ResourceNotFoundException("Product is required");
        }
        var existedProduct = productRepository.findByName(ProductDTO.getName());
        if (existedProduct != null && !existedProduct.getId().equals(id)) {
            throw new ResourceNotFoundException("Product name is existed");
        }
        //update Product
        var product = productRepository.findById(id).orElse(null);
        if (product == null)
        {
            throw new ResourceNotFoundException("Product Not Found");
        }
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path path = Paths.get(uploadDirectory, fileName);
                Files.write(path, image.getBytes());
                product.setImage(fileName);
            } catch (IOException e) {
                throw new ResourceNotFoundException("Error Save Image: " + e.getMessage());
            }
        }
        product.setName(ProductDTO.getName());
        product.setDescription(ProductDTO.getDescription());
        product.setPrice(ProductDTO.getPrice());
        product.setStatus(ProductDTO.getStatus());
        product.setCategory_id(ProductDTO.getCategory_id());
        product = productRepository.save(product);
        //convert to dto
        var updateProductDTO = new ProductDTO();
        updateProductDTO.setId(product.getId());
        updateProductDTO.setName(product.getName());
        updateProductDTO.setDescription(product.getDescription());
        updateProductDTO.setPrice(product.getPrice());
        updateProductDTO.setStatus(product.getStatus());
        updateProductDTO.setCategory_id(product.getCategory_id());
        return updateProductDTO;
    }

    @Override
    public void deleteProduct(int id) {
        var category = productRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        this.productRepository.deleteById(id);
    }
}
