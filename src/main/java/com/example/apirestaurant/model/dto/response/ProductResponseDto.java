package com.example.apirestaurant.model.dto.response;

import com.example.apirestaurant.model.dto.CategoryWithoutProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private Double price;
    private List<CategoryWithoutProductDto> categories;
}
