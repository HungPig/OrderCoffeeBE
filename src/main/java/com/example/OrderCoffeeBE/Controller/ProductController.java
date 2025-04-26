package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Request.PostProductRequest;
import com.example.OrderCoffeeBE.Entity.products;
import com.example.OrderCoffeeBE.Service.impl.ProductServiceImpl;
import com.example.OrderCoffeeBE.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl _productService) {
        productService = _productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<products>>> getAllProducts() {
        List<products> products = productService.findAll();
        return ResponseEntity.ok(ApiResponse.success("GET Products success", products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<products>> getProductById(@PathVariable Integer id) {
       products findProduct = this.productService.findById(id);
       if(findProduct == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(ApiResponse.error("Product not found"));
       }
       return ResponseEntity.ok(ApiResponse.success("Get Product success", findProduct));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<products>> createProduct(
            @RequestPart("products") PostProductRequest productRequest,
            @RequestParam(value = "image", required = false) MultipartFile imageFile )
             {
        boolean isNameExist = this.productService.isNameExist(productRequest.getName());
        if (isNameExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Product name already exists"));
        }
        products newProduct = this.productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Create Product success", newProduct));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<products>> updateProduct(@PathVariable int id, @RequestBody products product) {

        products hungProduct = this.productService.updateProduct(product);
        if (hungProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found"));
        }
        return ResponseEntity.ok(ApiResponse.success("Update Product success", hungProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable int id) {
        products currentProduct = this.productService.findById(id);
        if (currentProduct != null) {
            this.productService.deleteProduct(currentProduct);
            return ResponseEntity.ok(ApiResponse.success("Delete Product success", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found"));
        }
    }
}