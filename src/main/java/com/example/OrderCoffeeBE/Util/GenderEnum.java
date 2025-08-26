package com.example.OrderCoffeeBE.Util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {
    MALE(0),
    FEMALE(1);
    private final int value;

    GenderEnum(int value) {
        this.value = value;
    }
    @JsonValue
    public int getValue() {
        return value;
    }
}
