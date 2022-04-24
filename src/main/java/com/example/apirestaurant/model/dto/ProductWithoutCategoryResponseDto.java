package com.example.apirestaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductWithoutCategoryResponseDto {

    private Long id;
    private String name;
    private Double price;
}
