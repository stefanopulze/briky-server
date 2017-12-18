package com.stefano.briky.utils;

import com.stefano.briky.model.Tags;
import org.springframework.stereotype.Component;

@Component
public class BrikyTagSlugger implements TagSlugger {

    @Override
    public String calculateSlug(String name) {
        return name != null ? name.toLowerCase() : null;
    }

    @Override
    public String calculateSlug(Tags tag) {
        String slug = null;

        if(tag != null && tag.getSlug() != null) {
            slug = tag.getSlug();
        } else if(tag != null && tag.getSlug() == null) {
            slug = tag.getName().toLowerCase();
            slug = slug.replaceAll("\\s", "-");
        }

        return slug;
    }
}
