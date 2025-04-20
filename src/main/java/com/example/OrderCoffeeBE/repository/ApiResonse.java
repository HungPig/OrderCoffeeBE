package com.example.OrderCoffeeBE.repository;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ApiResonse<T> {
    public String message;
    public boolean success;
    public T data;
    public ApiResonse(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
    public static <T> ApiResonse<T> success(String message, T data) {
        return new ApiResonse<>(message, true, data);
    }
    public static <T> ApiResonse<T> error(String message, String eMessage) {
        return new ApiResonse<>(message, false, null);
    }
}
