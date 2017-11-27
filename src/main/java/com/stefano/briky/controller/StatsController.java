package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.DashboardJson;
import com.stefano.briky.json.DatePagination;
import com.stefano.briky.repository.ExpencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {

    @Autowired
    ExpencesRepository expencesRepository;


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

        return result;
    }

}
