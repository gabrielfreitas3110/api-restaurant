package com.example.apirestaurant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {

    private ProductRequestId product;
    private Integer quantity;
    private Double price;
}
