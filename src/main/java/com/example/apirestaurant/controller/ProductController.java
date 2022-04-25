package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.request.ProductRequestDto;
import com.example.apirestaurant.model.dto.response.ProductResponseDto;
import com.example.apirestaurant.model.dto.ProductWithoutCategoryDto;
import com.example.apirestaurant.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService ProductService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok().body(modelMapper.map(ProductService.create(productRequestDto), ProductResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<ProductWithoutCategoryDto>> findAll() {
        return ResponseEntity.ok().body(ProductService.findAll().stream()
                .map(c -> modelMapper.map(c, ProductWithoutCategoryDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelMapper
                .map(ProductService.findById(id), ProductResponseDto.class));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto ProductDto) {
        return ResponseEntity.ok().body(modelMapper
                .map(ProductService.update(id, ProductDto), ProductResponseDto.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> delete(@PathVariable Long id) {
        ProductService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
