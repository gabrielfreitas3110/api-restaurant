package com.example.apirestaurant.model.dto;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryDtoTest {

    @Test
    public void should_Sucess_When_CorrectCreateCategoryDto() {
        CategoryDto c = new CategoryDto();
        assertNull(c.getName());
        c.setName("Food");
        assertEquals(c.getName(), "Food");
        c = new CategoryDto("Drink");
        assertEquals(c.getName(), "Drink");
        c = CategoryDto.builder().name("Combo").build();
        assertEquals(c.getName(), "Combo");
    }
}