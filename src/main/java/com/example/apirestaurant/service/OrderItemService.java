package com.example.apirestaurant.service;

import com.example.apirestaurant.model.OrderItem;
import com.example.apirestaurant.repository.OrderItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<OrderItem> create(List<OrderItem> orderItems) {
        List<OrderItem> obj = orderItemRepository.saveAll(orderItems);
        return obj;
    }
}
