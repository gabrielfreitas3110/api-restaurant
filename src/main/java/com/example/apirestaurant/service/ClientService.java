package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.dto.request.ClientUpdateRequestDto;
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

    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        Client obj = findByCpfOrCnpj(clientRequestDto.getCpfOrCnpj());
        if(obj != null)
            throw new DuplicatedObjectException("Client already exist! Id: " + obj.getId());
        return modelMapper.map(clientRepository
                .save(modelMapper.map(clientRequestDto, Client.class)),
                ClientResponseDto.class);
    }

    private Client findByCpfOrCnpj(String cpfOrCnpj) {
        return clientRepository.findByCpfOrCnpj(cpfOrCnpj);
    }

    public ClientResponseDto findById(Long id) {
        return modelMapper.map(clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found! Id: " + id)),
                ClientResponseDto.class);
    }

    public List<ClientResponseDto> findAll() {
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client, ClientResponseDto.class))
                .collect(Collectors.toList());
    }

    public ClientResponseDto update(Long id, ClientUpdateRequestDto clientUpdateRequestDto) {
        Client obj = modelMapper.map(findById(id), Client.class);
        updateData(obj, clientUpdateRequestDto);
        return modelMapper.map(clientRepository.save(obj), ClientResponseDto.class);
    }

    private void updateData(Client obj, ClientUpdateRequestDto clientDto) {
        if(!clientDto.getName().isEmpty())
            obj.setName(clientDto.getName());
    }
}
