package com.example.apirestaurant.service;

import com.example.apirestaurant.model.City;
import com.example.apirestaurant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City getByName(String name) {
        return cityRepository.findByName(name);
    }
}
