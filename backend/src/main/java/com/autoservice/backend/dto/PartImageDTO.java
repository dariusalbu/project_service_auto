package com.autoservice.backend.dto;

import com.autoservice.backend.model.PartImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PartImageDTO {
    private Long id;
    private String imageUrl;

    public static PartImageDTO mapToDTO(PartImage image) {
        if(image == null) return null;
        PartImageDTO dto = new PartImageDTO();
        dto.setId(image.getId());
        dto.setImageUrl(image.getImageUrl());
        return dto;
    }
}
