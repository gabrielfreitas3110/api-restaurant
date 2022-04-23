package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
