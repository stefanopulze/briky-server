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
        if(tag != null && tag.getSlug() != null) {
            return tag.getSlug();
        } else if(tag != null && tag.getSlug() == null) {
            return tag.getName().toLowerCase();
        } else {
            return null;
        }
    }
}
