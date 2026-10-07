package com.autoservice.backend.controller;

import com.autoservice.backend.dto.InventoryDTO;
import com.autoservice.backend.dto.ServiceLocationDTO;
import com.autoservice.backend.enums.PartCategory;
import com.autoservice.backend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createStock(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> searchInvetoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.searchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> modifyInventory(@PathVariable Long id, @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.modifyStock(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvetory(@PathVariable Long id) {
        inventoryService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<Page<InventoryDTO>> searchInventoryWithFilters(
            @RequestParam(required = false) String partCode,
            @RequestParam(required = false) String partName,
            @RequestParam(required = false) String partManufacturer,
            @RequestParam(required = false) PartCategory partCategory,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String locationName,
            @RequestParam(required = false) String locationAddress,
            @RequestParam(required = false) String locationCity,
            @RequestParam(required = false) Boolean inStockOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<InventoryDTO> res = inventoryService.multiFilterSearch(
                partCode,
                partName,
                partManufacturer,
                partCategory,
                minPrice,
                maxPrice,
                locationName,
                locationAddress,
                locationCity,
                inStockOnly,
                page,
                size);

        return ResponseEntity.ok(res);
    }
}
