package com.example.OrderCoffeeBE.Controller;

import com.example.OrderCoffeeBE.Entity.tables;
import com.example.OrderCoffeeBE.Service.impl.TableServiceImpl;
import com.example.OrderCoffeeBE.repository.TableRepository;
import com.example.OrderCoffeeBE.response.ApiResponse;
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
    private final TableRepository tableRepository;
    @GetMapping
    public ResponseEntity<ApiResponse<List<tables>>> getTables() {
        List<tables> TableEntities = tableRepository.findAll();
        return ResponseEntity.ok(ApiResponse.success("GET Tables success", TableEntities));
    }
    @GetMapping("/id")
    public ResponseEntity<ApiResponse<tables>> getTableById(@RequestParam int id) {
       tables findTables = tableRepository.findById(id).get();
       if(findTables == null) {
           return ResponseEntity.ok(ApiResponse.error("Table not found"));
       }
        return ResponseEntity.ok(ApiResponse.success("GET Table success", findTables));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<tables>> addTable(@RequestBody tables tables) {
        tables save = tableRepository.save(tables);
        return ResponseEntity.ok(ApiResponse.success("ADD Table success", save));
    }
    @PatchMapping()
    public ResponseEntity<ApiResponse<tables>> updateTable(@RequestBody tables tables) {
        tables save = tableRepository.save(tables);
        return ResponseEntity.ok(ApiResponse.success("UPDATE Table success", save));
    }
    @DeleteMapping()
    public ResponseEntity<ApiResponse<tables>> deleteTable(@RequestParam int id) {
        tables currentTables = this.tableService.findById(id);
        if (currentTables != null) {
            this.tableService.deleteTable(currentTables);
            return ResponseEntity.ok(ApiResponse.success("Delete Product success", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found"));
        }
    }
}
