package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.dao.StatsDao;
import com.stefano.briky.json.*;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.utils.BrikyDateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StatsController {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    StatsDao statsDao;

    @Autowired
    ModelMapper modelMapper;


    @Transactional
    @RequestMapping(value = "/stat/monthly/expense")
    public List<TagExpenseValue> monthlyExpenses(
            @AuthenticationPrincipal LoggedUser user,
            MonthFilter pagination) {

        if (null == pagination) {
            pagination = BrikyDateUtils.buildCurrentMonth();
        }

        return statsDao.monthlyTagValue(user.getId(), pagination.getYear(), pagination.getMonth());
    }

    @RequestMapping(value = "/stat/values/year")
    public List<MonthValue> valueYear(
            @AuthenticationPrincipal LoggedUser user,
            MonthFilter pagination) {

        if (null == pagination) {
            pagination = BrikyDateUtils.buildCurrentMonth();
        }

        return expenseService.yearlySum(user, pagination);
    }

    @RequestMapping(value = "/stat/dashboard")
    public DashboardJson dashboardStats(MonthFilter pagination, @AuthenticationPrincipal LoggedUser user) {
        DashboardJson result = new DashboardJson();

        if (null == pagination || pagination.isEmpty()) {
            pagination = BrikyDateUtils.buildCurrentMonth();
        }

        MonthFilter previousMonth = pagination.clone().previousMonth();

        result.setCurrentMonthValue(
                expencesRepository.monthlySum(user.getId(), pagination)
        );
        result.setPreviousMonthValue(
                expencesRepository.monthlySum(user.getId(), previousMonth)
        );

        List<Expenses> lastExpenses = expenseService.findLast(10);
        result.setLastExpenses(
                lastExpenses.stream()
                        .map(expense -> modelMapper.map(expense, ExpenseJson.class))
                        .collect(Collectors.toList()));

        //result.setMonthTagValue(monthlyExpenses(user, previousMonth));
        return result;
    }

}
