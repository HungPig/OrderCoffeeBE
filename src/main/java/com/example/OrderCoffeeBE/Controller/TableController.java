package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Model.Tables;
import com.example.OrderCoffeeBE.Service.impl.TableServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TableController {
    private final TableServiceImpl tableService;
    @GetMapping
    public ResponseEntity<List<Tables>> getTables() {
        return ResponseEntity.status(HttpStatus.OK).body(this.tableService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tableService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Tables> addTable(@RequestBody Tables tables) {
        Tables newTables = this.tableService.createTable(tables);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTables);
    }
    @PatchMapping()
    public ResponseEntity<Tables> updateTable(@PathVariable int id, @RequestBody Tables table) {
        table.setId(id);
        Tables updateTable = this.tableService.updateTable(table);
        return ResponseEntity.status(HttpStatus.OK).body(updateTable);
    }
    @DeleteMapping()
    public ResponseEntity<Tables> deleteTable(@RequestParam int id) {
        this.tableService.deleteTable(id);
        return ResponseEntity.ok(null);
    }
}
