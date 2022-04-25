package com.example.apirestaurant.model.dto.response;

import com.example.apirestaurant.model.dto.CityWithoutStateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StateResponseDto {

    private Long id;
    private List<CityWithoutStateDto> cities;
}
