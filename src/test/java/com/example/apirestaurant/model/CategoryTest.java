package com.example.apirestaurant.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryTest {

    @Test
    public void should_Sucess_When_CorrectCreateCategory() {
        Category c = new Category();
        assertNull(c.getId());
        c.setId(1L);
        assertEquals(c.getId(), Optional.of(1L).get());
        c = new Category(2L, "Food", null);
        assertEquals(c.getName(), "Food");
        c = Category.builder().id(3L).build();
        assertEquals(c.getId(), Optional.of(3L).get());
    }
}