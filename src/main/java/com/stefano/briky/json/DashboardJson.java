package com.stefano.briky.json;

import java.util.List;

public class DashboardJson {

    private Double monthExpensesValue;
    private List<ExpenceJson> lastExpenses;
    private List<TagExpenseValue> monthTagValue;


    public Double getMonthExpensesValue() {
        return monthExpensesValue;
    }

    public void setMonthExpensesValue(Double monthExpensesValue) {
        this.monthExpensesValue = monthExpensesValue;
    }

    public List<ExpenceJson> getLastExpenses() {
        return lastExpenses;
    }

    public void setLastExpenses(List<ExpenceJson> lastExpenses) {
        this.lastExpenses = lastExpenses;
    }

    public List<TagExpenseValue> getMonthTagValue() {
        return monthTagValue;
    }

    public void setMonthTagValue(List<TagExpenseValue> monthTagValue) {
        this.monthTagValue = monthTagValue;
    }
}
