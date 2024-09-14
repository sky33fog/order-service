package com.example.order_service.service;

import com.example.order_service.model.Order;
import com.example.order_service.repository.InMemoryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InMemoryOrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order save(Order order) {
        return repository.save(order);
    }
}
