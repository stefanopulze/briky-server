package com.stefano.briky.json;

import java.util.ArrayList;
import java.util.List;

public class TagDetailJson extends TagJson {

    private Integer usedCount = 0;
    private List<ExpenseJson> expenses = new ArrayList<>();

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public List<ExpenseJson> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseJson> expenses) {
        this.expenses = expenses;
    }
}
