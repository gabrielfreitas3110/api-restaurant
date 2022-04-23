package com.example.apirestaurant.controller;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        List<Category> categories = List.of(
                Category.builder().id(1L).name("Food").build(),
                Category.builder().id(2L).name("Drink").build());
        return categories;
    }
}
