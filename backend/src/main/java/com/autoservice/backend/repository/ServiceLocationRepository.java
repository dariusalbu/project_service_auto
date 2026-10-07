package com.autoservice.backend.repository;

import com.autoservice.backend.model.ServiceLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceLocationRepository extends JpaRepository<ServiceLocation, Long> {
    @Query("SELECT s FROM ServiceLocation s WHERE " +
            "(:locationName IS NULL OR s.locationName = :locationName) AND " +
            "(:address IS NULL OR s.address = :address) AND " +
            "(:city IS NULL OR s.city = :city)")
    Page<ServiceLocation> filterLocations(@Param("locationName") String locationName,
                                          @Param("address") String address,
                                          @Param("city") String city,
                                          Pageable pageable);
}
