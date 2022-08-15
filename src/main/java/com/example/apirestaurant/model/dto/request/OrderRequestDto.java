package com.example.apirestaurant.model.dto.request;

import com.example.apirestaurant.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequestDto {

    private Payment payment;

    private ClientRequestId client;

    private AddressRequestId deliveryAddress;

    private List<OrderItemRequestDto> itens;
}
