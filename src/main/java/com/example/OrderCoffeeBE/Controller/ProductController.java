package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.DTO.Request.ProductUpdateRequest;
import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.Service.IProductService;
import com.example.OrderCoffeeBE.Service.ProductServiceImpl;
import com.example.OrderCoffeeBE.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "findall",
            method = RequestMethod.GET,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public ResponseEntity<?> findAll() {
        try {
            List<Product> products = productService.findAll();
            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping
//    Product addProduct(@RequestBody Product product) {
//        return productService.CreateProduct(product);
//    }
//    @GetMapping
//    List<Product> getProduct() {
//        return productService.getProduct();
//    }
//    @PutMapping("/{productId}")
//    Product updateProduct(@PathVariable int id, @RequestBody ProductUpdateRequest request) {
//        return productService.(id, request);
//    }
}

