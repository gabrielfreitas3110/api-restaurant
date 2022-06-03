package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.dto.ProductWithoutCategoryDto;
import com.example.apirestaurant.model.dto.request.ProductRequestDto;
import com.example.apirestaurant.model.dto.response.ProductResponseDto;
import com.example.apirestaurant.repository.ProductRepository;
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

    public Page<ProductWithoutCategoryDto> getAllPaged(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return productRepository.findAll(pageRequest)
                .map(p -> modelMapper.map(p, ProductWithoutCategoryDto.class));
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

    public Page<ProductWithoutCategoryDto> search(String name, List<String> stringCategoryIds, Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        List<Long> categoryIds = stringCategoryIds.stream().map(c -> Long.parseLong(c)).collect(Collectors.toList());
        name = name.replace(" ","");
        List<Category> categories = categoryService.getAllById(categoryIds);
        return productRepository.findDistinctByNameContainingIgnoreCaseAndCategoriesIn(name, categories, pageRequest).map(p -> modelMapper.map(p, ProductWithoutCategoryDto.class));
    }
}
