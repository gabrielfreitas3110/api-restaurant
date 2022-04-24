package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.dto.CategoryRequestDto;
import com.example.apirestaurant.repository.CategoryRepository;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Category create(CategoryRequestDto categoryRequestDto) {
        verifyDuplicate(categoryRequestDto.getName());
        return categoryRepository.save(modelMapper.map((categoryRequestDto), Category.class));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found! Id: "+id));
    }

    public Category update(Long id, CategoryRequestDto categoryDto) {
        Category obj = findById(id);
        verifyDuplicate(categoryDto.getName());
        obj.setName(categoryDto.getName());
        return categoryRepository.save(obj);
    }

    public void delete(Long id) {
        Category obj = findById(id);
        categoryRepository.delete(obj);
    }

    private void verifyDuplicate(String name) {
        Category obj = categoryRepository.findByName(name);
        if(obj != null)
            throw new DuplicatedObjectException("Category with name '"+ name + "' already exits! Id: " + obj.getId());
    }
}
