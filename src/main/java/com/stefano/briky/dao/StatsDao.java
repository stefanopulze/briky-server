package com.stefano.briky.dao;

import com.stefano.briky.json.TagExpenseValue;

import java.util.List;

public interface StatsDao {

    List<TagExpenseValue> monthlyTagValue(int userId, int year, int month);

}
