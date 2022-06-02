package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.request.AddressRequestDto;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.dto.request.ClientUpdateRequestDto;
import com.example.apirestaurant.model.dto.response.ClientResponseDto;
import com.example.apirestaurant.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        ClientResponseDto obj = clientService.create(clientRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> findAll(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
        return ResponseEntity.ok().body(clientService.getAllPaged(page, size, direction, orderBy));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> update(@PathVariable Long id, @RequestBody @Valid ClientUpdateRequestDto clientUpdateRequestDto) {
        return ResponseEntity.ok().body(clientService.update(id, clientUpdateRequestDto));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> addAddress(@PathVariable Long id, @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok().body(clientService.addAddress(id, addressRequestDto));
    }

    @PatchMapping(value = "/{id}/address/{address_id}")
    public ResponseEntity<ClientResponseDto> removeAddress(@PathVariable Long id, @PathVariable Long address_id) {
        return ResponseEntity.ok().body(clientService.removeAddress(id, address_id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
