package com.stefano.briky.json.filter;

import java.util.List;

public class SerieFilter {

    private String name;
    private List<Integer> tags;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

}
