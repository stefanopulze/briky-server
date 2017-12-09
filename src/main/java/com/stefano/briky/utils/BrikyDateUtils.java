package com.stefano.briky.utils;

import com.stefano.briky.controller.DateRequestParam;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BrikyDateUtils {

    public static DateRequestParam buildCurrentMonth() {
        Calendar calendar = GregorianCalendar.getInstance();

        return new DateRequestParam(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)
        );
    }
}
