package com.example.order_service.repository;

import com.example.order_service.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository {

    private final List<Order> repository = new ArrayList<>();

    public List<Order> findAll() {
        return new ArrayList<>(repository);
    }

    public Order save(Order order) {
        repository.add(order);
        return order;
    }
}
