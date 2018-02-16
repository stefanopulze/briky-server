package com.stefano.briky.json;

import java.util.List;

public class DashboardJson {

    private Integer currentMonthValue;
    private Integer previousMonthValue;

    private List<ExpenseJson> lastExpenses;
    private List<TagExpenseValue> monthTagValue;

    public Integer getCurrentMonthValue() {
        return currentMonthValue;
    }

    public void setCurrentMonthValue(Integer currentMonthValue) {
        this.currentMonthValue = currentMonthValue;
    }

    public Integer getPreviousMonthValue() {
        return previousMonthValue;
    }

    public void setPreviousMonthValue(Integer previousMonthValue) {
        this.previousMonthValue = previousMonthValue;
    }

    public List<ExpenseJson> getLastExpenses() {
        return lastExpenses;
    }

    public void setLastExpenses(List<ExpenseJson> lastExpenses) {
        this.lastExpenses = lastExpenses;
    }

    public List<TagExpenseValue> getMonthTagValue() {
        return monthTagValue;
    }

    public void setMonthTagValue(List<TagExpenseValue> monthTagValue) {
        this.monthTagValue = monthTagValue;
    }
}
