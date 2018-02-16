package com.stefano.briky.json.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter from date to date and tags
 */
public class TagDateFilter extends DateRangeFilter {

    private List<Integer> tags = new ArrayList<>();

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

}
