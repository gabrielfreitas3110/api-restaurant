package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.dto.ProductWithoutCategoryDto;
import com.example.apirestaurant.model.dto.request.ProductRequestDto;
import com.example.apirestaurant.model.dto.response.ProductResponseDto;
import com.example.apirestaurant.repository.ProductRepository;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product p = modelMapper.map(productRequestDto, Product.class);
        verifyDuplicate(p.getName());
        p.setCategories(categoryService.verifyCategories(p.getCategories()));
        return modelMapper.map(productRepository.save(p), ProductResponseDto.class);
    }

    public List<ProductWithoutCategoryDto> getAll() {
        return productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductWithoutCategoryDto.class))
                .collect(Collectors.toList());
    }

    public ProductResponseDto getById(Long id) {
        return modelMapper.map(productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found! Id: " + id)), ProductResponseDto.class);
    }

    public ProductResponseDto update(Long id, ProductRequestDto ProductDto) {
        Product obj = modelMapper.map(getById(id), Product.class);
        verifyDuplicate(ProductDto.getName());
        obj.setName(ProductDto.getName());
        return modelMapper.map(productRepository.save(obj), ProductResponseDto.class);
    }

    public void delete(Long id) {
        Product obj = modelMapper.map(getById(id), Product.class);
        productRepository.delete(obj);
    }

    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    private void verifyDuplicate(String name) {
        Product obj = productRepository.findByName(name);
        if(obj != null)
            throw new DuplicatedObjectException("Product with name '"+ name + "' already exist! Id: " + obj.getId());
    }

    protected List<Product> verifyProducts(List<Product> products) {
        List<Product> productList = new ArrayList<>();
        if(products != null) {
            productList = products.stream().map(p -> {
                Product po = getByName(p.getName());
                return po != null ? po : p;
            }).collect(Collectors.toList());
        }
        return productList;
    }
}
