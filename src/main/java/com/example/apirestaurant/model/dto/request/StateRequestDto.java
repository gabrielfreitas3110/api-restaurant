package com.example.apirestaurant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StateRequestDto {

    @NotBlank(message = "Required field")
    @Size(min = 5, max = 50, message = "The size must be between 5 and 50 characters.")
    private String name;
}
