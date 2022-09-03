package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Address;
import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.AddressRequestDto;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.dto.request.ClientUpdateRequestDto;
import com.example.apirestaurant.model.dto.response.ClientResponseDto;
import com.example.apirestaurant.model.dto.response.OrderResponseDto;
import com.example.apirestaurant.model.enums.ClientTypeEnum;
import com.example.apirestaurant.model.enums.PaymentStatusEnum;
import com.example.apirestaurant.repository.ClientRepository;
import com.example.apirestaurant.service.exception.BadRequestException;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    public ClientResponseDto create(ClientRequestDto clientRequestDto) {
        Client obj = modelMapper.map(clientRequestDto, Client.class);
        obj.setType(ClientTypeEnum.toEnum(clientRequestDto.getType()));
        return modelMapper.map(clientRepository.save(obj), ClientResponseDto.class);
    }

    private Client getByCpfCnpj(String cpfCnpj) {
        return clientRepository.findByCpfCnpj(cpfCnpj);
    }

    public ClientResponseDto getById(Long id) {
        return modelMapper.map(clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found! Id: " + id)),
                ClientResponseDto.class);
    }

    public Page<ClientResponseDto> getAllPaged(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest)
                .map(client -> modelMapper.map(client, ClientResponseDto.class));
    }

    public ClientResponseDto update(Long id, ClientUpdateRequestDto clientUpdateRequestDto) {
        Client obj = modelMapper.map(getById(id), Client.class);
        updateData(obj, clientUpdateRequestDto);
        return modelMapper.map(clientRepository.save(obj), ClientResponseDto.class);
    }

    private void updateData(Client obj, ClientUpdateRequestDto clientDto) {
        if(!clientDto.getName().isEmpty())
            obj.setName(clientDto.getName());
        if(!clientDto.getEmail().isEmpty())
            obj.setEmail(clientDto.getEmail());
    }

    public ClientResponseDto addAddress(Long id, AddressRequestDto address) {
        Client obj = modelMapper.map(getById(id), Client.class);
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
        Client obj = modelMapper.map(getById(id), Client.class);
        Address addressObj = addressService.getById(address_id);
        obj.removeAddress(addressObj);
        addressService.delete(address_id);
        return modelMapper.map(clientRepository.save(obj), ClientResponseDto.class);
    }

    public void delete(Long id) {
        Client obj = modelMapper.map(getById(id), Client.class);
        List<OrderResponseDto> orders = orderService.getByClientId(id);
        for(OrderResponseDto order : orders) {
            if(!order.getPayment().getPaymentStatus().getId().equals(PaymentStatusEnum.PAID.getId()))
                throw new BadRequestException("Can't delete client with unpaid order!");
        }
        clientRepository.deleteById(id);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found! Id: " + id));
    }
}
