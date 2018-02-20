package com.stefano.briky.utils.slug;

import com.stefano.briky.model.Categories;
import com.stefano.briky.model.Tags;

public interface Slug {

    String from(String value);

    String from(Categories category);

    String from(Tags tag);
}
