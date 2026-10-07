package com.autoservice.backend.service;

import com.autoservice.backend.dto.ServiceLocationDTO;
import com.autoservice.backend.model.ServiceLocation;
import com.autoservice.backend.repository.ServiceLocationRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ServiceLocationService {
    private final ServiceLocationRepository serviceLocationRepository;

    public ServiceLocationDTO createServiceLocation(ServiceLocationDTO dto) {
        ServiceLocation srv = new ServiceLocation();

        srv.setCity(dto.getCity());
        srv.setAddress(dto.getAddress());
        srv.setLocationName(dto.getLocationName());

        ServiceLocation srvWithId = serviceLocationRepository.save(srv);
        dto.setId(srvWithId.getId());

        return dto;
    }

    public ServiceLocationDTO findServiceLocationById(Long id) {
        ServiceLocation srv = serviceLocationRepository.findById(id).orElseThrow(() -> new RuntimeException("The service location with the specified id does not exist"));
        return ServiceLocationDTO.mapToDTO(srv);
    }

    public void deleteServiceLocation(Long id) {
        if(!serviceLocationRepository.existsById(id)) {
            throw new RuntimeException("The service location you tried to delete does not exist!");
        }

        serviceLocationRepository.deleteById(id);
    }

    public ServiceLocationDTO updateServiceLocation(Long id, ServiceLocationDTO dto) {
        ServiceLocation srv = serviceLocationRepository.findById(id).orElseThrow(() -> new RuntimeException("The service location you are trying to update does not exist!"));

        srv.setLocationName(dto.getLocationName());
        srv.setCity(dto.getCity());
        srv.setAddress(dto.getAddress());

        ServiceLocation upt = serviceLocationRepository.save(srv);
        return ServiceLocationDTO.mapToDTO(upt);
    }

    public Page<ServiceLocationDTO> filterLocations(String locationName, String address, String city, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ServiceLocation> entityPage = serviceLocationRepository.filterLocations(locationName, address, city, pageable);

        return entityPage.map(ServiceLocationDTO::mapToDTO);
    }
}
