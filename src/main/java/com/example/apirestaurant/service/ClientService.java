package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.dto.response.ClientResponseDto;
import com.example.apirestaurant.repository.ClientRepository;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Client create(ClientRequestDto clientRequestDto) {
        Client obj = findByCpfOrCnpj(clientRequestDto.getCpfOrCnpj());
        if(obj != null)
            throw new DuplicatedObjectException("Client already exist! Id: " + obj.getId());
        Client p = modelMapper.map(clientRequestDto, Client.class);
        return clientRepository.save(p);
    }

    public Client findByCpfOrCnpj(String cpfOrCnpj) {
        return clientRepository.findByCpfOrCnpj(cpfOrCnpj);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found! Id: "+id));
    }

    public List<ClientResponseDto> findAll() {
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, ClientResponseDto.class))
                .collect(Collectors.toList());
    }

}
