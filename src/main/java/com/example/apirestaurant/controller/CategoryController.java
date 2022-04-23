package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.dto.CategoryDto;
import com.example.apirestaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Category save(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(categoryService.update(id, categoryDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
