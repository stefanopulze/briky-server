package com.stefano.briky.dao;

import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.json.MonthValue;

import java.util.List;

public interface ExpenseDao {

    List<MonthValue> yearlySum(Integer userId, MonthFilter filter);

}
