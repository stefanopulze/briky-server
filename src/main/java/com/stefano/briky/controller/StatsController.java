package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.dao.StatsDao;
import com.stefano.briky.json.DashboardJson;
import com.stefano.briky.json.DatePagination;
import com.stefano.briky.json.ExpenceJson;
import com.stefano.briky.json.TagExpenseValue;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.utils.BrikyDateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping(value = "/stat/tag/most-used")
    public void mostUsedTag(DatePagination pagination) {
    }

    @RequestMapping(value = "/stat/tag/expense")
    public void expenseForTag(DatePagination pagination) {

    }

    @Transactional
    @RequestMapping(value = "/stat/monthly/expense")
    public List<TagExpenseValue> monthlyExpenses(
            @AuthenticationPrincipal LoggedUser user,
            DateRequestParam pagination) {

        if(null == pagination) {
            pagination = BrikyDateUtils.buildCurrentMonth();
        }

        return statsDao.monthlyTagValue(user.getId(), pagination.getYear(), pagination.getMonth());
    }

    @RequestMapping(value = "/stat/dashboard")
    public DashboardJson dashboardStats(DateRequestParam pagination, @AuthenticationPrincipal LoggedUser user) {
        DashboardJson result = new DashboardJson();

        if(null == pagination || pagination.isEmpty()) {
            pagination = BrikyDateUtils.buildCurrentMonth();
        }

        DateRequestParam previousMonth = pagination.clone().previousMonth();

        result.setCurrentMonthValue(
                expencesRepository.monthlySum(user.getId(), pagination)
        );
        result.setPreviousMonthValue(
                expencesRepository.monthlySum(user.getId(), previousMonth)
        );

        List<Expenses> lastExpenses = expenseService.findLast(10);
        result.setLastExpenses(
                lastExpenses.stream()
                        .map(expense -> modelMapper.map(expense, ExpenceJson.class))
                        .collect(Collectors.toList()));

        result.setMonthTagValue(monthlyExpenses(user, previousMonth));
        return result;
    }

}
