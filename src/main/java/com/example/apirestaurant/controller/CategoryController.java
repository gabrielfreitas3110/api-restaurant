package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import com.example.apirestaurant.model.dto.response.CategoryResponseDto;
import com.example.apirestaurant.model.dto.CategoryWithoutProductDto;
import com.example.apirestaurant.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok().body(modelMapper
                .map(categoryService.create(categoryRequestDto), CategoryResponseDto.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoryWithoutProductDto>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll().stream()
                .map(c -> modelMapper.map(c, CategoryWithoutProductDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(modelMapper
                .map(categoryService.findById(id), CategoryResponseDto.class));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok().body(modelMapper
                .map(categoryService.update(id, categoryRequestDto), CategoryResponseDto.class));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
