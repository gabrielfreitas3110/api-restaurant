package com.example.apirestaurant.model.dto.response;

import com.example.apirestaurant.model.Address;
import com.example.apirestaurant.model.OrderItem;
import com.example.apirestaurant.model.Payment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponseDto {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date Instant;

    private Payment payment;

    private ClientResponseDto client;

    private Address deliveryAddress;

    private List<OrderItem> itens;
}
