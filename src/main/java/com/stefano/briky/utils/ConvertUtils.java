package com.stefano.briky.utils;

import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertUtils {

    @Autowired
    ModelMapper modelMapper;

    public Expenses toExpense(ExpenseJson json) {
        Expenses expense = modelMapper.map(json, Expenses.class);
        return expense;
    }

    public Tags toTag(TagJson json) {
        return modelMapper.map(json, Tags.class);
    }



}
