package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getById(id));
    }

}
