package com.stefano.briky.controller;

import com.stefano.briky.controller.exception.NotFoundException;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.filter.DataFilter;
import com.stefano.briky.json.filter.EpochFilter;
import com.stefano.briky.json.filter.SeriesFilter;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ConvertUtils convertUtils;

    // list
    @RequestMapping(value = "/expenses", method = RequestMethod.GET)
    public List<Expenses> listExpenses(DataFilter filter) {
        return expenseService.findLast(filter.getSize());
    }

    @RequestMapping(value = "/expenses/value", method = RequestMethod.POST)
    public List<ExpenseValue> listExpensesValue(@RequestBody EpochFilter filter) {
        return expenseService.groupedValue(filter);
    }

    @RequestMapping(value = "/expenses/value/series", method = RequestMethod.POST)
    public Map<String, List<ExpenseValue>> listExpensesSeriesValue(@RequestBody SeriesFilter filter) {
        return expenseService.groupedValue(filter);
    }

    // create
    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    public Expenses createExpense(@RequestBody ExpenseJson json) {
        Expenses expense = convertUtils.toExpense(json);
        expenseService.create(expense);

        return expense;
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
    public Expenses updateExpense(@RequestBody ExpenseJson json,
                                  @PathVariable("id") int id) throws NotFoundException {
        return expenseService.update(id, json);
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
    public void deleteExpense(@PathVariable("id") int id) throws NotFoundException {
        expenseService.delete(id);
    }

}
