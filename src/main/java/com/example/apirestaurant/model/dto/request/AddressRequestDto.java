package com.example.apirestaurant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDto {

    private String street;
    private Integer number;
    private String cep;
    private String cityName;
    private String stateName;
}
