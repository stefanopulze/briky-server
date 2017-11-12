package com.stefano.briky.json;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePagination {

    public static final DatePagination monthPagination() {
        DatePagination pagination = new DatePagination();
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        pagination.setStartDate(calendar.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        pagination.setEndDate(calendar.getTime());

        return pagination;
    }

    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
