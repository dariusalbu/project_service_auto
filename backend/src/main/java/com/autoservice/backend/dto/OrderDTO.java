package com.autoservice.backend.dto;

import com.autoservice.backend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long idPart;
    private Integer quantity;
    private Double price;

    public static OrderDTO mapToDTO(Order order) {
        if (order == null) return null;
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setQuantity(order.getQuantity());
        dto.setPrice(order.getPrice());
        dto.setIdPart(order.getIdPart());

        return dto;
    }
}
