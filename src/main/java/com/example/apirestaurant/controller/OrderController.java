package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.request.OrderRequestDto;
import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto obj = orderService.create(orderRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

}
