package com.stefano.briky.json;

import com.stefano.briky.model.Tags;

public class TagExpenseValue extends Tags {

    private int count;
    private Double value;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
