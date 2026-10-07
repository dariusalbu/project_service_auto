package com.autoservice.backend.service;

import com.autoservice.backend.dto.InventoryDTO;
import com.autoservice.backend.enums.PartCategory;
import com.autoservice.backend.model.Inventory;
import com.autoservice.backend.model.Part;
import com.autoservice.backend.model.ServiceLocation;
import com.autoservice.backend.repository.InventoryRepository;
import com.autoservice.backend.repository.PartRepository;
import com.autoservice.backend.repository.ServiceLocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final PartRepository partRepository;
    private final ServiceLocationRepository serviceLocationRepository;

    public InventoryDTO createStock(InventoryDTO dto) {
        ServiceLocation location = serviceLocationRepository.findById(dto.getServiceLocation().getId())
                .orElseThrow(() -> new RuntimeException("The desired location does not exists in order for me to add a stock in it!"));

        Part part = partRepository.findById(dto.getPart().getId())
                .orElseThrow(() -> new RuntimeException("The desired part does not exist in our catalogues!"));

        Inventory inventory = new Inventory();
        inventory.setServiceLocation(location);
        inventory.setPart(part);
        inventory.setCurrentStock(dto.getCurrentStock());

        Inventory saved = inventoryRepository.save(inventory);
        dto.setId(saved.getId());

        return dto;
    }

    public InventoryDTO searchById(Long id) {
        return InventoryDTO.mapToDTO(inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("The inventory you are trying to search for does not exist!")));
    }

    @Transactional
    public InventoryDTO modifyStock(Long id, InventoryDTO dto) {
        Inventory inv = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("The inventory you are trying to modify does not exist!"));
        inv.setCurrentStock(dto.getCurrentStock());
        inv.setPart(partRepository.findById(dto.getPart().getId()).orElseThrow(() -> new RuntimeException("The part is missing from our catalogue!")));
        inv.setServiceLocation(serviceLocationRepository.findById(dto.getServiceLocation().getId()).orElseThrow(() -> new RuntimeException("The service location you are trying to modify is missing !")));

        Inventory saved = inventoryRepository.save(inv);
        InventoryDTO invDTO = InventoryDTO.mapToDTO(saved);

        return invDTO;
    }

    public void deleteStock(Long id) {
        if(!inventoryRepository.existsById(id)) {
            throw new RuntimeException("The stock you are trying to delete does not exist!");
        }
        inventoryRepository.deleteById(id);
    }

    public Page<InventoryDTO> multiFilterSearch(String partCode,
                                          String partName,
                                          String partManufacturer,
                                          PartCategory partCategory,
                                          Double minPrice,
                                          Double maxPrice,
                                          String locationName,
                                          String locationAddress,
                                          String locationCity,
                                          Boolean inStockOnly,
                                          int page,
                                          int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventory> saved = inventoryRepository.filterOnPartsAndLocations(
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
                pageable
                );

        return saved.map(InventoryDTO::mapToDTO);
    }


}
