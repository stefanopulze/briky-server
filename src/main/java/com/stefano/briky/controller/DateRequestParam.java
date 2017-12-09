package com.stefano.briky.controller;

public class DateRequestParam implements Cloneable {

    public static final int MONTH_COUNT = 11;
    private Integer year;
    private Integer month;

    public DateRequestParam() {
    }

    public DateRequestParam(Integer year) {
        this.year = year;
    }

    public DateRequestParam(Integer year, Integer month) {
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

    public DateRequestParam addYear(int year) {
       this.year += year;
       return this;
    }

    // TODO eseguire test integrativi
    public DateRequestParam addMonth(int month) {
        this.month += month;

        if(this.month > MONTH_COUNT) {
            this.year += this.month / MONTH_COUNT;
            this.month = this.month % MONTH_COUNT;
        }

        return this;
    }

    public DateRequestParam previousMonth() {
        this.month--;

        if(this.month < 0) {
            this.year--;
            this.month = MONTH_COUNT;
        }

        return this;
    }

    @Override
    protected DateRequestParam clone() {
        DateRequestParam object = new DateRequestParam();
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
}
