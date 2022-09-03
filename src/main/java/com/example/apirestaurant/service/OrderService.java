package com.example.apirestaurant.service;

import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.repository.OrderRepository;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderResponseDto getById(Long id) {
        return modelMapper.map(orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found! Id: " + id)), OrderResponseDto.class);
    }

    protected List<OrderResponseDto> getByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(order -> modelMapper.map(order, OrderResponseDto.class))
                .collect(Collectors.toList());
    }
}
