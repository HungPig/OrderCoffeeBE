package com.example.OrderCoffeeBE.Service.impl;

import com.example.OrderCoffeeBE.Entity.Tables;
import com.example.OrderCoffeeBE.Service.TableService;
import com.example.OrderCoffeeBE.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service("TableService")
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    @Override
    public List<Tables> findAll() {
        return tableRepository.findAll();
    }

    @Override
    public Tables createTable(Tables tables) {
        return tableRepository.save(tables);
    }

    @Override
    public Tables updateTable(Tables updateTables) {
        Tables currentTables = this.findById(updateTables.getId());
        if(currentTables != null) {
            currentTables.setId(updateTables.getId());
            currentTables.setStatus(updateTables.getStatus());
            tableRepository.save(currentTables);
        }
        return updateTables;
    }

    @Override
    public void deleteTable(int id) {
        tableRepository.deleteById(id);
    }

    @Override
    public Tables findById(int id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Table not found with id: " + id));
    }
}
