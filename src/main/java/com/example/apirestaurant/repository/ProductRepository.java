package com.example.apirestaurant.repository;

import com.example.apirestaurant.model.Category;
import com.example.apirestaurant.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories c WHERE lower(p.name) LIKE %?1% AND c IN ?2")
    @Transactional(readOnly = true)
    Page<Product> search(String name, List<Category> categories, Pageable pageable);

    //Exactly equals Search
    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingIgnoreCaseAndCategoriesIn(String name, List<Category> categories, Pageable pageable);
}
