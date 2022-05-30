package com.example.apirestaurant.service;

import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.repository.OrderRepository;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderResponseDto getById(Long id) {
        return modelMapper.map(orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found! Id: "+id)), OrderResponseDto.class);
    }

}
