package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.dto.ProductRequestDto;
import com.example.apirestaurant.repository.ProductRepository;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository ProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    public Product create(ProductRequestDto productRequestDto) {
        Product p = modelMapper.map(productRequestDto, Product.class);
        verifyDuplicate(p.getName());
        p.setCategories(verifyCategories(p.getCategories()));
        return ProductRepository.save(p);
    }

    public List<Product> findAll() {
        return ProductRepository.findAll();
    }

    public Product findById(Long id) {
        return ProductRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found! Id: "+id));
    }

    public Product update(Long id, ProductRequestDto ProductDto) {
        Product obj = findById(id);
        verifyDuplicate(ProductDto.getName());
        obj.setName(ProductDto.getName());
        return ProductRepository.save(obj);
    }

    public void delete(Long id) {
        Product obj = findById(id);
        ProductRepository.delete(obj);
    }

    private void verifyDuplicate(String name) {
        Product obj = ProductRepository.findByName(name);
        if(obj != null)
            throw new DuplicatedObjectException("Product with name '"+ name + "' already exits! Id: " + obj.getId());
    }

    private List<Category> verifyCategories(List<Category> categories) {
        List<Category> categoryList = categories.stream().map(c -> {
            Category ca = categoryService.findByName(c.getName());
            return ca != null ? ca : c;
        }).collect(Collectors.toList());
        return categoryList;
    }
}
