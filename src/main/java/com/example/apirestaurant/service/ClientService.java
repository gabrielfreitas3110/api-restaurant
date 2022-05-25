package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Client create(ClientRequestDto clientRequestDto) {
        Client p = modelMapper.map(clientRequestDto, Client.class);
        return clientRepository.save(p);
    }
}
