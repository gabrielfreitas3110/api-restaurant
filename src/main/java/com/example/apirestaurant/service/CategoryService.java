package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import com.example.apirestaurant.repository.CategoryRepository;
import com.example.apirestaurant.service.exception.ConstraintViolationException;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Category create(CategoryRequestDto categoryRequestDto) {
        Category c = modelMapper.map(categoryRequestDto, Category.class);
        verifyDuplicate(categoryRequestDto.getName());
        c.setProducts(verifyProducts(c.getProducts()));
        return categoryRepository.save(c);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found! Id: " + id));
    }

    public Category update(Long id, CategoryRequestDto categoryDto) {
        Category obj = getById(id);
        verifyDuplicate(categoryDto.getName());
        obj.setName(categoryDto.getName());
        return categoryRepository.save(obj);
    }

    public void delete(Long id) {
        Category obj = getById(id);
        if(!obj.getProducts().isEmpty())
            throw new ConstraintViolationException("Can't delete category " + obj.getName()
                    + ", because it have linked products");
        categoryRepository.delete(obj);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    private void verifyDuplicate(String name) {
        Category obj = findByName(name);
        if(obj != null)
            throw new DuplicatedObjectException("Category with name '"+ name + "' already exist! Id: " + obj.getId());
    }

    private List<Product> verifyProducts(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        if(products != null) {
            productList = products.stream().map(p -> {
                Product po = productService.findByName(p.getName());
                return po != null ? po : p;
            }).collect(Collectors.toList());
        }
        return productList;
    }
}
