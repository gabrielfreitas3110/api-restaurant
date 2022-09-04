package com.example.apirestaurant.model.dto;

import com.example.apirestaurant.model.dto.request.CategoryRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class CategoryRequestDtoTest {

    @Test
    public void should_Sucess_When_CorrectCreateCategoryRequestDto() {
        CategoryRequestDto c = new CategoryRequestDto();
        assertNull(c.getName());
        c.setName("Food");
        assertEquals(c.getName(), "Food");
        c = new CategoryRequestDto("Drink", null);
        assertEquals(c.getName(), "Drink");
        c = CategoryRequestDto.builder().name("Combo").build();
        assertEquals(c.getName(), "Combo");
    }
}