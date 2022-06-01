package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Address;
import com.example.apirestaurant.model.City;
import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.State;
import com.example.apirestaurant.model.dto.request.AddressRequestDto;
import com.example.apirestaurant.model.dto.request.StateRequestDto;
import com.example.apirestaurant.service.exception.BadRequestException;
import com.example.apirestaurant.repository.AddressRepository;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    @Autowired
    private ClientService clientService;

    public Address create(Address address) {
        return addressRepository.save(address);
    }

    public Address getById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Address not found! Id: " + id));
    }

    protected Address verifyAddress(Client obj, AddressRequestDto address) {
        if(address.getCityName().equals(null) || address.getCityName().isEmpty())
            throw new BadRequestException("Must give a city.");
        if(address.getStateName().equals(null) || address.getStateName().isEmpty())
            throw new BadRequestException("Must give a state.");
        City cityObj = cityService.getByName(address.getCityName());
        State stateObj = stateService.getByName(address.getStateName());
        if(stateObj == null) {
            if(cityObj != null) {
                throw new BadRequestException("City already have a state associated");
            }
            stateObj = stateService.create(StateRequestDto.builder().name(address.getStateName()).build());
        }
        if(cityObj == null) {
            cityObj = City.builder().name(address.getCityName()).state(stateObj).build();
        }
        clientService.verifyDuplicateAddressClient(obj, address);
        cityObj.setState(stateObj);
        return buildAddress(address, cityObj, obj);
    }

    protected Boolean addressEquals(Address obj1, AddressRequestDto obj2) {
        return (obj1.getStreet().equals(obj2.getStreet()) && obj1.getCep().equals(obj2.getCep()) &&
                obj1.getNumber().equals(obj2.getNumber()) && obj1.getCity().getName().equals(obj2.getCityName()) &&
                obj1.getCity().getState().getName().equals(obj2.getStateName()));
    }

    protected Address buildAddress(AddressRequestDto address, City city, Client client) {
        return Address.builder().street(address.getStreet()).number(address.getNumber())
                .cep(address.getCep()).city(city).client(client).build();
    }

    public void delete(Long id) {
        Address obj = getById(id);
        obj.setClient(null);
        addressRepository.delete(obj);
    }
}
