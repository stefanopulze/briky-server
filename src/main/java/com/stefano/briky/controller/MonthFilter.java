package com.stefano.briky.controller;

public class MonthFilter implements Cloneable {

    public static final int MONTH_COUNT = 11;
    private Integer year;
    private Integer month;

    public MonthFilter() {
    }

    public MonthFilter(Integer year) {
        this.year = year;
    }

    public MonthFilter(Integer year, Integer month) {
        this.year = year;
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getMonthSql() {
        return this.month + 1;
    }

    public MonthFilter addYear(int year) {
       this.year += year;
       return this;
    }

    // TODO eseguire test integrativi
    public MonthFilter addMonth(int month) {
        this.month += month;

        if(this.month > MONTH_COUNT) {
            this.year += this.month / MONTH_COUNT;
            this.month = this.month % MONTH_COUNT;
        }

        return this;
    }

    public MonthFilter previousMonth() {
        this.month--;

        if(this.month < 0) {
            this.year--;
            this.month = MONTH_COUNT;
        }

        return this;
    }

    @Override
    protected MonthFilter clone() {
        MonthFilter object = new MonthFilter();
        object.year = this.year;
        object.month = this.month;
        return object;
    }

    @Override
    public String toString() {
        return "DateRequestParam{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }

    public boolean isEmpty() {
        return year == null;
    }

    public boolean isValidMonth() {
        return month != null && month >= 0 && month <= 11;
    }
}
