package com.example.order_service.web.controller;

import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${app.kafka.kafkaOrderTopic}")
    private String orderEventTopic;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        kafkaTemplate.send(orderEventTopic, new OrderEvent(order.getProduct(), order.getQuantity()));

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(order));
    }
}