package com.stefano.briky.utils;

import com.stefano.briky.model.Tags;

public interface TagSlugger {

    String calculateSlug(String name);

    String calculateSlug(Tags tag);

}
