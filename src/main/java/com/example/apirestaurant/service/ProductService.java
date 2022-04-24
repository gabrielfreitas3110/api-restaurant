package com.example.apirestaurant.service;

import com.example.apirestaurant.model.Product;
import com.example.apirestaurant.model.dto.ProductRequestDto;
import com.example.apirestaurant.repository.ProductRepository;
import com.example.apirestaurant.service.exception.DuplicatedObjectException;
import com.example.apirestaurant.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository ProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Product create(ProductRequestDto productRequestDto) {
        verifyDuplicate(productRequestDto.getName());
        return ProductRepository.save(modelMapper.map(productRequestDto, Product.class));
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
}
