package com.stefano.briky.service;

import com.stefano.briky.model.Categories;
import com.stefano.briky.repository.CategoryRepository;
import com.stefano.briky.utils.SecurityUtils;
import com.stefano.briky.utils.slug.Slug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired @Lazy
    Slug slug;

    @Autowired
    CategoryRepository repository;

    public Categories getOrCreate(Categories category) {
        int userId = SecurityUtils.getUser().getId();

        if(null == category) {
            return null;
        }

        String slug = this.slug.from(category);

        Categories findCat = repository.findBySlugAndUserId(slug, userId);

        if(findCat == null) {
            category.setUserId(userId);
            category.setSlug(slug);
            findCat = repository.save(category);
        }

        return findCat;
    }
}
