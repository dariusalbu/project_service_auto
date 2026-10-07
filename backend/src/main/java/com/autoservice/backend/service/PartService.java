package com.autoservice.backend.service;

import com.autoservice.backend.dto.PartDTO;
import com.autoservice.backend.model.Part;
import com.autoservice.backend.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;

    public PartDTO createPart(PartDTO dto) {
        Part part = new Part();
        part.setCode(dto.getCode());
        part.setName(dto.getName());
        part.setManufacturer(dto.getManufacturer());
        part.setCategory(dto.getCategory());
        part.setPrice(dto.getPrice());

        Part savedPart = partRepository.save(part);
        dto.setId(savedPart.getId());
        return dto;
    }

    public PartDTO getPartById(Long id) {
        Part part = partRepository.findById(id).orElseThrow(() -> new RuntimeException("The part with the searched id is missing"));
        return PartDTO.mapToDTO(part);
    }

    public void deletePart(Long id) {
        Part part = partRepository.findById(id).orElseThrow(()-> new RuntimeException("The part does not exist!"));
        part.setActive(false);
        partRepository.save(part);
    }

    public PartDTO updatePart(Long id, PartDTO dto) {
        Part part = partRepository.findById(id).orElseThrow(() -> new RuntimeException("The part does not exist!"));

        part.setCode(dto.getCode());
        part.setName(dto.getName());
        part.setManufacturer(dto.getManufacturer());
        part.setCategory(dto.getCategory());
        part.setPrice(dto.getPrice());

        Part updatedPart = partRepository.save(part);
        return PartDTO.mapToDTO(updatedPart);
    }

    public List<PartDTO> getAllParts() {
        return partRepository.findAll().stream().map(part -> {
            PartDTO dto = new PartDTO();
            dto.setId(part.getId());
            dto.setCode(part.getCode());
            dto.setName(part.getName());
            dto.setManufacturer(part.getManufacturer());
            dto.setCategory(part.getCategory());
            dto.setPrice(part.getPrice());
            return dto;
        }).toList();
    }
}
