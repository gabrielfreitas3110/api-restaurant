package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Payment;
import com.example.apirestaurant.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }
}
