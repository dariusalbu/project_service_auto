package com.autoservice.backend.repository;

import com.autoservice.backend.enums.PartCategory;
import com.autoservice.backend.model.Inventory;
import com.autoservice.backend.model.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE " +
            "(:partCode IS NULL OR LOWER(i.part.code) LIKE LOWER(CONCAT('%', :partCode, '%'))) AND " +
            "(:partName IS NULL OR LOWER(i.part.name) LIKE LOWER(CONCAT('%', :partName, '%'))) AND " +
            "(:partManufacturer IS NULL OR LOWER(i.part.manufacturer) LIKE LOWER(CONCAT('%', :partManufacturer, '%'))) AND " +
            "(:partCategory IS NULL OR i.part.category = :partCategory) AND " +
            "(:minPrice IS NULL OR i.part.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR i.part.price <= :maxPrice) AND " +
            "(:locationName IS NULL OR LOWER(i.serviceLocation.locationName) LIKE LOWER(CONCAT('%', :locationName, '%'))) AND " +
            "(:locationAddress IS NULL OR LOWER(i.serviceLocation.address) LIKE LOWER(CONCAT('%', :locationAddress, '%'))) AND " +
            "(:locationCity IS NULL OR LOWER(i.serviceLocation.city) LIKE LOWER(CONCAT('%', :locationCity, '%'))) AND " +
            "(:inStockOnly IS NULL OR :inStockOnly = FALSE OR i.currentStock > 0)")
    Page<Inventory> filterOnPartsAndLocations(
            @Param("partCode") String partCode,
            @Param("partName") String partName,
            @Param("partManufacturer") String partManufacturer,
            @Param("partCategory") PartCategory partCategory,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("locationName") String locationName,
            @Param("locationAddress") String locationAddress,
            @Param("locationCity") String locationCity,
            @Param("inStockOnly") Boolean inStockOnly,
            Pageable pageable
            );
}
