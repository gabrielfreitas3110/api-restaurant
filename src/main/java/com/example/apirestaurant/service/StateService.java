package com.example.apirestaurant.service;

import com.example.apirestaurant.model.State;
import com.example.apirestaurant.model.dto.request.StateRequestDto;
import com.example.apirestaurant.repository.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    ModelMapper modelMapper;

    public State getByName(String stateName) {
        return stateRepository.findByName(stateName);
    }

    public State create(StateRequestDto stateRequestDto) {
        return stateRepository.save(modelMapper.map(stateRequestDto, State.class));
    }
}
