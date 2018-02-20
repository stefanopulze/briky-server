package com.stefano.briky.repository;

import com.stefano.briky.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

    Categories findBySlugAndUserId(String slug, int userId);

}
