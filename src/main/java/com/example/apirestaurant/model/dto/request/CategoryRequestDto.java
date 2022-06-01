package com.example.apirestaurant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryRequestDto {

    @NotBlank(message = "Required field")
    @Size(min = 5, max = 15, message = "The size must be between 5 and 15 characters.")
    private String name;
    private List<ProductRequestDto> products;
}
