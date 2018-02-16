package com.stefano.briky.json;

import java.time.LocalDate;

public class ExpenseValue {

    private LocalDate date;
    private Integer value;

    public ExpenseValue() {
    }

    public ExpenseValue(EpochGroup group, Object[] row) {
        if(group == EpochGroup.YEAR) {
            date = LocalDate.of(((Number) row[0]).intValue(), 1, 1);
            value = ((Number) row[1]).intValue();
        } else if (group == EpochGroup.MONTH) {
            date = LocalDate.of(
                    ((Number) row[0]).intValue(),
                    ((Number) row[1]).intValue(),
                    1);
            value = ((Number) row[2]).intValue();
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
