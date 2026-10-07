package com.autoservice.backend.controller;

import com.autoservice.backend.dto.ServiceLocationDTO;
import com.autoservice.backend.model.ServiceLocation;
import com.autoservice.backend.service.ServiceLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-locations")
public class ServiceLocationController {
    private final ServiceLocationService serviceLocationService;

    @PostMapping
    public ResponseEntity<ServiceLocationDTO> createServiceLocation(@RequestBody ServiceLocationDTO dto) {
        ServiceLocationDTO savedServLoc = serviceLocationService.createServiceLocation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedServLoc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceLocationDTO> getServiceLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceLocationService.findServiceLocationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceLocationDTO> updateServiceLocation(@PathVariable Long id, @RequestBody ServiceLocationDTO dto) {
        ServiceLocationDTO updtPart = serviceLocationService.updateServiceLocation(id, dto);
        return ResponseEntity.ok(updtPart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceLocation(@PathVariable Long id) {
        serviceLocationService.deleteServiceLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ServiceLocationDTO>> filteredSearch(
            @RequestParam(required = false) String locationName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ServiceLocationDTO> res = serviceLocationService.filterLocations(locationName, address, city, page, size);
        return ResponseEntity.ok(res);
    }
}
