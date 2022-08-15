package com.example.apirestaurant.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
        c = Category.builder()
                .id(3L)
                .products(List.of(new Product(4L, "Pasta", 6.99, List.of(c)))).build();
        assertEquals(c.getProducts().size(), 1);
        assertEquals(c.getProducts().get(0).getId(), Optional.of(4L).get());
        assertEquals(c.getProducts().get(0).getName(), "Pasta");
        assertEquals(c.getProducts().get(0).getPrice(), Optional.of(6.99).get());
        assertEquals(c.getProducts().get(0).getCategories().get(0).getId(), c.getId());
        Product p = new Product();
        p.setName("Pizza");
        c.setProducts(List.of(p));
        assertEquals(c.getProducts().get(0).getName(),p.getName());
    }
}