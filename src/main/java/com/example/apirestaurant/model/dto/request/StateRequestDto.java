package com.example.apirestaurant.model.dto.request;

import com.example.apirestaurant.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StateRequestDto {

    private String name;
    private List<City> cities;
}
