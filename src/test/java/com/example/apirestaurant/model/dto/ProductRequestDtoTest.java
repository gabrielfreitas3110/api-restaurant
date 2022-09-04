package com.example.apirestaurant.model.dto;

import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import com.example.apirestaurant.model.dto.request.ProductRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class ProductRequestDtoTest {

    @Test
    public void should_Sucess_When_CorrectCreateProductRequestDto() {
        ProductRequestDto p = new ProductRequestDto();
        assertNull(p.getName());
        p.setName("Pizza");
        assertEquals(p.getName(), "Pizza");
        p = new ProductRequestDto("Pizza", 4.99, List.of(new CategoryRequestDto("Food", null)));
        assertEquals(p.getName(), "Pizza");
        assertEquals(p.getPrice(), Double.valueOf(4.99));
        assertEquals(p.getCategories().get(0).getName(), "Food");
        p = ProductRequestDto.builder().name("Combo").build();
        assertEquals(p.getName(), "Combo");
    }
}