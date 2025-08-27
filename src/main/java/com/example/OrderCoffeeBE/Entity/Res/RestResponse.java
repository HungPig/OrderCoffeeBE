package com.example.OrderCoffeeBE.Entity.Res;

import lombok.Data;

@Data
public class RestResponse<T> {
    private int statusCode;
    private String error;
    private Object message;
    private T data;
}
