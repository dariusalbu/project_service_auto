package com.autoservice.backend.service;

import com.autoservice.backend.dto.OrderDTO;
import com.autoservice.backend.model.Order;
import com.autoservice.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderDTO createOrder(OrderDTO dto) {
        Order order = new Order();
        order.setPrice(dto.getPrice());
        order.setQuantity(dto.getQuantity());
        order.setIdPart(dto.getIdPart());

        Order saved = orderRepository.save(order);
        dto.setId(saved.getId());

        return dto;
    }

    public OrderDTO findOrderById(Long id) {
        return OrderDTO.mapToDTO(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("The order you are searching for does not exist!")));
    }

    public OrderDTO updateOrder(Long id, OrderDTO dto) {
        Order orderFromRepo = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("The order you are trying to update does not exist"));
        orderFromRepo.setIdPart(dto.getIdPart());
        orderFromRepo.setPrice(dto.getPrice());
        orderFromRepo.setQuantity(dto.getQuantity());

        Order saved = orderRepository.save(orderFromRepo);

        return OrderDTO.mapToDTO(saved);
    }

    public void deleteOrder(Long id) {
        if(!orderRepository.existsById(id)) {
            throw new RuntimeException("The order you are trying to delete does not exists!");
        }
        orderRepository.deleteById(id);
    }
}
