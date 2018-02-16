package com.stefano.briky.dao;

import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.MonthValue;
import com.stefano.briky.json.filter.EpochFilter;

import java.util.List;

public interface ExpenseDao {

    List<MonthValue> yearlySum(Integer userId, MonthFilter filter);

    List<ExpenseValue> groupedValue(EpochFilter filter);
}
