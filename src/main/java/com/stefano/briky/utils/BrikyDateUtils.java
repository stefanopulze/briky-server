package com.stefano.briky.utils;

import com.stefano.briky.controller.MonthFilter;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BrikyDateUtils {

    public static MonthFilter buildCurrentMonth() {
        Calendar calendar = GregorianCalendar.getInstance();

        return new MonthFilter(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)
        );
    }
}
