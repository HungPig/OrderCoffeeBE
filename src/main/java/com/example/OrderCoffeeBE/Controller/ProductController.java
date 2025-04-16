package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.Product;
import com.example.OrderCoffeeBE.Service.ProductService;
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
    private ProductService productService;

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

    @RequestMapping(value = "find/{id}",
            method = RequestMethod.GET,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Product product = productService.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "create",
            method = RequestMethod.POST,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json")
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            productService.CreateProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while creating product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "edit",
            method = RequestMethod.PUT,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<?> update(@RequestBody Product product) {
        try {
            productService.UpdateProduct(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.DELETE,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE,
            headers = "Accept=application/json"
    )
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            productService.DeleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

