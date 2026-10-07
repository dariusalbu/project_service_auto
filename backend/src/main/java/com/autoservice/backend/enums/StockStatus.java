package com.autoservice.backend.enums;

public enum StockStatus {
    IN_STOCK,        // > 5
    LOW_STOCK,       // 1 - 5
    OUT_OF_STOCK,    // 0
    DISCONTINUED     // part.isActive() == false
}
