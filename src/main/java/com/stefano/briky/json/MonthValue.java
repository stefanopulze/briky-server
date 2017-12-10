package com.stefano.briky.json;

public class MonthValue {

    private int year;
    private int month;
    private Integer value;

    public MonthValue() {
    }

    public MonthValue(Object[] row) {
        if(row.length >= 1) {
            year = ((Number) row[0]).intValue();
        }

        if(row.length >= 2) {
            month = ((Number) row[1]).intValue();
        }

        if(row.length >= 3) {
            value = ((Number) row[2]).intValue();
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
