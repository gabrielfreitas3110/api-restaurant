package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.dto.CategoryWithoutProductDto;
import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import com.example.apirestaurant.model.dto.response.CategoryResponseDto;
import com.example.apirestaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto obj = categoryService.create(categoryRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CategoryWithoutProductDto>> findAll() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok().body(categoryService.update(id, categoryRequestDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
