package com.autoservice.backend.controller;

import com.autoservice.backend.dto.OrderDTO;
import com.autoservice.backend.model.Order;
import com.autoservice.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
       OrderDTO saved =  orderService.createOrder(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrderById(@PathVariable Long id, @RequestBody OrderDTO dto) {
        OrderDTO updated = orderService.updateOrder(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
