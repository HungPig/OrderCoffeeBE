package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Dto.Product.PostProductDTO;
import com.example.OrderCoffeeBE.Dto.Product.ProductDTO;
import com.example.OrderCoffeeBE.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product createProduct(PostProductDTO request ,MultipartFile image) throws IOException;
    ProductDTO updateProduct(int id, ProductDTO ProductDTO, MultipartFile image) throws IOException;
    void deleteProduct(int id);
}
