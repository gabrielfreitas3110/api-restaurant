package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Address;
import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.AddressRequestDto;
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

    @Autowired
    private AddressService addressService;

    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        Client obj = getByCpfOrCnpj(clientRequestDto.getCpfOrCnpj());
        if(obj != null)
            throw new DuplicatedObjectException("Client already exist! Id: " + obj.getId());
        return modelMapper.map(clientRepository
                .save(modelMapper.map(clientRequestDto, Client.class)),
                ClientResponseDto.class);
    }

    private Client getByCpfOrCnpj(String cpfOrCnpj) {
        return clientRepository.findByCpfOrCnpj(cpfOrCnpj);
    }

    public ClientResponseDto findById(Long id) {
        return modelMapper.map(clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found! Id: " + id)),
                ClientResponseDto.class);
    }

    public List<ClientResponseDto> getAll() {
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

    public ClientResponseDto addAddress(Long id, AddressRequestDto address) {
        Client obj = modelMapper.map(findById(id), Client.class);
        Address addressObj = addressService.verifyAddress(obj, address);
        addressService.create(addressObj);
        obj.addAddress(addressObj);
        clientRepository.save(obj);
        return modelMapper.map(obj, ClientResponseDto.class);
    }

    protected void verifyDuplicateAddressClient(Client obj, AddressRequestDto address) {
        for(Address ad : obj.getAddresses()) {
            if(addressService.addressEquals(ad, address))
                throw new DuplicatedObjectException("Client " + obj.getName() + ", already have this address");
        }
    }

    public ClientResponseDto removeAddress(Long id, Long address_id) {
        Client obj = modelMapper.map(findById(id), Client.class);
        Address addressObj = addressService.getById(address_id);
        obj.removeAddress(addressObj);
        addressService.delete(address_id);
        return modelMapper.map(clientRepository.save(obj), ClientResponseDto.class);
    }

}
