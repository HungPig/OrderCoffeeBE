package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Dto.Product.PostProductDTO;
import com.example.OrderCoffeeBE.Dto.Product.ProductDTO;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.Service.impl.ProductServiceImpl;
import com.example.OrderCoffeeBE.Util.Anotation.ApiMessage;
import com.example.OrderCoffeeBE.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    @ApiMessage("Fetch Product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findAll());
    }

    @GetMapping("/{id}")
    @ApiMessage("Fetch By Id Product")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product findProduct = this.productService.findById(id);
        return ResponseEntity.ok(findProduct);
    }

    @PostMapping
    @ApiMessage("Create Product")
    public ResponseEntity<Product> createProduct(@ModelAttribute PostProductDTO requestProduct, MultipartFile image) throws IOException {
        Product saveProduct = productService.createProduct(requestProduct, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
    }

    @PatchMapping("/{id}")
    @ApiMessage("Update Product")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @ModelAttribute ProductDTO updateProduct, MultipartFile image) throws IOException {
        updateProduct.setId(id);
        ProductDTO updatedProduct = productService.updateProduct(id,updateProduct, image);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Delete Product")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
       this.productService.deleteProduct(id);
       return ResponseEntity.ok(null);
    }
}