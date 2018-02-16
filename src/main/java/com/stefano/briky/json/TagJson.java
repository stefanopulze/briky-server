package com.stefano.briky.json;

public class TagJson {

    private int id;
    private String name;

    public TagJson() {
    }

    public TagJson(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
