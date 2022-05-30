package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.ProductWithoutCategoryDto;
import com.example.apirestaurant.model.dto.request.ProductRequestDto;
import com.example.apirestaurant.model.dto.response.ProductResponseDto;
import com.example.apirestaurant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto obj = productService.create(productRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<ProductWithoutCategoryDto>> findAll() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductRequestDto ProductDto) {
        return ResponseEntity.ok().body(productService.update(id, ProductDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
