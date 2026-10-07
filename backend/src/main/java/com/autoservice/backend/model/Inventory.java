package com.autoservice.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Inventory", uniqueConstraints = {@UniqueConstraint(columnNames = {"service_id", "part_id"})})
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceLocation serviceLocation;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    private Integer currentStock;

}
