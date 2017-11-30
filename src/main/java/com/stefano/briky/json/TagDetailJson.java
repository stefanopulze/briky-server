package com.stefano.briky.json;

import java.util.ArrayList;
import java.util.List;

public class TagDetailJson extends TagJson {

    private Integer usedCount = 0;
    private List<ExpenceJson> expenses = new ArrayList<>();

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public List<ExpenceJson> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenceJson> expenses) {
        this.expenses = expenses;
    }
}
