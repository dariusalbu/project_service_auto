package com.autoservice.backend.controller;

import com.autoservice.backend.dto.PartDTO;
import com.autoservice.backend.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parts")
@CrossOrigin(origins = "*")

public class PartController {
    private final PartService partService;

    @GetMapping
    public ResponseEntity<List<PartDTO>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartDTO> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @PostMapping
    public ResponseEntity<PartDTO> createPart(@RequestBody PartDTO partDTO) {
        PartDTO savedPart = partService.createPart(partDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartDTO> updatePart(@PathVariable Long id, @RequestBody PartDTO partDTO) {
        PartDTO updatedPart = partService.updatePart(id, partDTO);
        return ResponseEntity.ok(updatedPart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }
}
