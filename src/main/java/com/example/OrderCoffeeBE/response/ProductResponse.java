package com.example.OrderCoffeeBE.response;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        int price,
        int status,
        int categoryId,
        LocalDateTime createdAt
) {
}
