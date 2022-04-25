package com.example.apirestaurant.model.dto.response;

import com.example.apirestaurant.model.dto.StateWithoutCityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityResponseDto {

    private Long id;
    private StateWithoutCityDto state;
}
