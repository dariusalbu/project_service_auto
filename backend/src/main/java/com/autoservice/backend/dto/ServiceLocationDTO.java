package com.autoservice.backend.dto;

import com.autoservice.backend.model.ServiceLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocationDTO {
    private Long id;
    private String locationName;
    private String address;
    private String city;

    public static ServiceLocationDTO mapToDTO(ServiceLocation srv) {
        if(srv == null) return null;
        ServiceLocationDTO dto = new ServiceLocationDTO();
        dto.setId(srv.getId());
        dto.setAddress(srv.getAddress());
        dto.setLocationName(srv.getLocationName());
        dto.setCity(srv.getCity());

        return dto;
    }

}