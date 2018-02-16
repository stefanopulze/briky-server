package com.stefano.briky.service;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.controller.MonthFilter;
import com.stefano.briky.dao.ExpenseDao;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.MonthValue;
import com.stefano.briky.json.filter.EpochFilter;
import com.stefano.briky.json.filter.SeriesFilter;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    ExpenseDao expenseDao;

    @Autowired @Lazy
    TagService tagService;

    public List<Expenses> findLast(int limit) {
        LoggedUser user = SecurityUtils.getUser();

        return expencesRepository.findLast(user.getId(), PageRequest.of(0, limit));

    }

    public List<Expenses> findLastByTagId(int limit, int tagId) {
        return expencesRepository.findLastByTagId(tagId, PageRequest.of(0, limit));
    }

    public Expenses create(Expenses expense, LoggedUser principal) {
        expense.setUserId(principal.getId());
        expense.setUpdatedAt(LocalDateTime.now());

        if(null == expense.getCreatedAt()) {
            expense.setCreatedAt(LocalDateTime.now());
        }

        List<Tags> userTags = tagService.createIfNotExists(expense.getTags(), principal);
        expense.setTags(userTags);

        return expencesRepository.save(expense);
    }


    public List<MonthValue> yearlySum(LoggedUser user, MonthFilter pagination) {
        return expenseDao.yearlySum(user.getId(), pagination);
    }

    public List<ExpenseValue> groupedValue(EpochFilter filter) {
        return expenseDao.groupedValue(filter);
    }


    public Map<String, List<ExpenseValue>> groupedValue(SeriesFilter filter) {
        Map<String, List<ExpenseValue>> results = new HashMap<>();

        filter.getSeries().forEach(serie -> {
            EpochFilter epochFilter = new EpochFilter(filter, serie);
            results.put(serie.getName(), groupedValue(epochFilter));
        });


        return results;
    }
}
