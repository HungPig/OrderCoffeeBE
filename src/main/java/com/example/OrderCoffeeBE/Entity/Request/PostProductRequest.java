package com.example.OrderCoffeeBE.Entity.Request;

import com.example.OrderCoffeeBE.Entity.products;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public record PostProductRequest(
        String name,
        String description,
        int price,
        MultipartFile file,
        String imageUrl,
        int status,
        int category_id
) {
}
