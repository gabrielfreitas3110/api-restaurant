package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.dto.CategoryWithoutProductDto;
import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import com.example.apirestaurant.model.dto.response.CategoryResponseDto;
import com.example.apirestaurant.repository.CategoryRepository;
import com.example.apirestaurant.service.exception.ConstraintViolationException;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        Category c = modelMapper.map(categoryRequestDto, Category.class);
        verifyDuplicate(categoryRequestDto.getName());
        c.setProducts(productService.verifyProducts(c.getProducts()));
        return modelMapper.map(categoryRepository.save(c), CategoryResponseDto.class);
    }

    public Page<CategoryWithoutProductDto> getAllPaged(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest)
                .map(c -> modelMapper.map(c, CategoryWithoutProductDto.class));
    }

    public CategoryResponseDto getById(Long id) {
        return modelMapper.map(categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found! Id: " + id)), CategoryResponseDto.class);
    }

    public CategoryResponseDto update(Long id, CategoryRequestDto categoryDto) {
        Category obj = modelMapper.map(getById(id), Category.class);
        verifyDuplicate(categoryDto.getName());
        obj.setName(categoryDto.getName());
        return modelMapper.map(categoryRepository.save(obj), CategoryResponseDto.class);
    }

    public void delete(Long id) {
        Category obj = modelMapper.map(getById(id), Category.class);
        if(!obj.getProducts().isEmpty())
            throw new ConstraintViolationException("Can't delete category " + obj.getName()
                    + ", because it have linked products");
        categoryRepository.delete(obj);
    }

    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    private void verifyDuplicate(String name) {
        Category obj = getByName(name);
        if(obj != null)
            throw new DuplicatedObjectException("Category with name '"+ name + "' already exist! Id: " + obj.getId());
    }

    protected List<Category> verifyCategories(List<Category> categories) {
        List<Category> categoryList = new ArrayList<>();
        if(categories != null) {
            categoryList = categories.stream().map(c -> {
                Category ca = getByName(c.getName());
                return ca != null ? ca : c;
            }).collect(Collectors.toList());
        }
        return categoryList;
    }

    public List<Category> getAllById(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }
}
