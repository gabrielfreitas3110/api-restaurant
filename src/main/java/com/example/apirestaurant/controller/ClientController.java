package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.dto.response.ClientResponseDto;
import com.example.apirestaurant.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody ClientRequestDto clientRequestDto) {
        return ResponseEntity.ok().body(clientService.create(clientRequestDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> findAll() {
        return ResponseEntity.ok().body(clientService.findAll());
    }
}
