package com.example.apirestaurant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDto {

    @NotBlank(message = "Required field")
    @Size(min = 5, max = 50, message = "The size must be between 5 and 50 characters.")
    private String street;
    @NotBlank(message = "Required field")
    @Size(min = 1, max = 5, message = "The size must be between 1 and 5 characters.")
    private Integer number;
    private String cep;
    private String cityName;
    private String stateName;
}
