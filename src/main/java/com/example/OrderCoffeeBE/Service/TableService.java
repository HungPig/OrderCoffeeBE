package com.example.OrderCoffeeBE.Service;

import com.example.OrderCoffeeBE.Entity.Tables;

import java.util.List;

public interface TableService {
    List<Tables> findAll();
    Tables createTable(Tables tables);
    Tables updateTable(Tables tables);
    void deleteTable(int id);
    Tables findById(int id);
}
