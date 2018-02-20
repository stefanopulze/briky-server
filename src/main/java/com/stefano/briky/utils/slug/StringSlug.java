package com.stefano.briky.utils.slug;

import com.stefano.briky.model.Categories;
import com.stefano.briky.model.Tags;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringSlug implements Slug {

    @Override
    public String from(String value) {
        if(value != null) {
            value = value.toLowerCase().trim();
            value = value.replaceAll("\\s", "-");
        }

        return value;
    }

    @Override
    public String from(Categories category) {
        if(category != null && StringUtils.isEmpty(category.getSlug())) {
            return from(category.getName());
        } else {
            return category.getSlug();
        }
    }

    @Override
    public String from(Tags tag) {
        if(tag != null && StringUtils.isEmpty(tag.getSlug())) {
            return from(tag.getName());
        } else {
            return tag.getSlug();
        }
    }
}
