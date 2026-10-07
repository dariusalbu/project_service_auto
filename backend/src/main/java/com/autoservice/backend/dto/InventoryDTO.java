package com.autoservice.backend.dto;

import com.autoservice.backend.enums.StockStatus;
import com.autoservice.backend.model.Inventory;
import com.autoservice.backend.model.Part;
import com.autoservice.backend.model.ServiceLocation;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private ServiceLocationDTO serviceLocation;
    private PartDTO part;
    private Integer currentStock;
    private StockStatus stockStatus;

    public static InventoryDTO mapToDTO(Inventory inv) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inv.getId());
        Part part = inv.getPart();
        ServiceLocation location = inv.getServiceLocation();
        PartDTO partDTO = PartDTO.mapToDTO(part);
        ServiceLocationDTO locationDTO = ServiceLocationDTO.mapToDTO(location);

        dto.setPart(partDTO);
        dto.setServiceLocation(locationDTO);
        dto.setCurrentStock(inv.getCurrentStock());

        if(!inv.getPart().isActive()) {
            dto.setStockStatus(StockStatus.DISCONTINUED);
        }else if(inv.getCurrentStock() > 5) {
            dto.setStockStatus(StockStatus.IN_STOCK);
        }else if(inv.getCurrentStock() > 0) {
            dto.setStockStatus(StockStatus.LOW_STOCK);
        }else {
            dto.setStockStatus(StockStatus.OUT_OF_STOCK);
        }

        return dto;
    }
}
