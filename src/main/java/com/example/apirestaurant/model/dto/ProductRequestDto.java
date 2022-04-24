package com.example.apirestaurant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequestDto {

    private String name;
    private Double price;
    private List<CategoryRequestDto> categories;
}