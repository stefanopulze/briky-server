package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.DashboardJson;
import com.stefano.briky.json.DatePagination;
import com.stefano.briky.json.ExpenceJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatsController {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ModelMapper modelMapper;


    @RequestMapping(value = "/stat/tag/most-used")
    public void mostUsedTag(DatePagination pagination) {
    }

    @RequestMapping(value = "/stat/tag/expense")
    public void expenseForTag(DatePagination pagination) {

    }

    @RequestMapping(value = "/stat/dashboard")
    public DashboardJson dashboardStats(DatePagination pagination, @AuthenticationPrincipal LoggedUser user) {
        DashboardJson result = new DashboardJson();

        pagination = DatePagination.monthPagination();

        result.setMonthExpensesValue(
                expencesRepository.monthlySum(user.getId(), pagination.getStartDate(), pagination.getEndDate())
        );

        List<Expenses> lastExpenses = expenseService.findLast(10);
        result.setLastExpenses(
                lastExpenses.stream()
                        .map(expense -> modelMapper.map(expense, ExpenceJson.class))
                        .collect(Collectors.toList()));

        return result;
    }

}
