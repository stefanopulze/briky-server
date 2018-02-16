package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.ExpenseValue;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.json.filter.EpochFilter;
import com.stefano.briky.json.filter.SeriesFilter;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.service.TagService;
import com.stefano.briky.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    TagService tagService;

    @Autowired
    ConvertUtils convertUtils;

    @RequestMapping(value = "/expenses/value", method = RequestMethod.POST)
    public List<ExpenseValue> listExpensesValue(@RequestBody EpochFilter filter) {
        return expenseService.groupedValue(filter);
    }

    @RequestMapping(value = "/expenses/value/series", method = RequestMethod.POST)
    public Map<String, List<ExpenseValue>> listExpensesSeriesValue(@RequestBody SeriesFilter filter) {
        return expenseService.groupedValue(filter);
    }

    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    public Expenses createExpense(@AuthenticationPrincipal LoggedUser principal,
                                  @RequestBody ExpenseJson json) {

        Expenses expense = convertUtils.toExpense(json);

        expenseService.create(expense, principal);

        return expense;
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
    public Expenses updateExpense(@AuthenticationPrincipal LoggedUser principal,
                                  @RequestBody ExpenseJson json,
                                  @PathVariable("id") int expenseId) throws AccessException {

        Expenses expense = expencesRepository.findByIdAndUserId(expenseId, principal.getId());

        if (null == expense) {
            throw new AccessException("L'id della spesa indicata non appartiene a te");
        }

        List<Tags> checkedTags = convertAndCreate(json.getTags(), principal);

        expense.setValue(json.getValue());
        expense.setLatitude(json.getLatitude());
        expense.setLongitude(json.getLongitude());
        expense.setAccuracy(json.getAccuracy());
        expense.setUpdatedAt(LocalDateTime.now());
        expense.setDescription(json.getDescription());
        expense.setTags(checkedTags);

        expencesRepository.save(expense);

        return expense;
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.DELETE)
    public void updateExpense(@AuthenticationPrincipal LoggedUser principal,
                              @PathVariable("id") int expenseId) throws AccessException {

        Expenses expense = expencesRepository.findByIdAndUserId(expenseId, principal.getId());

        if (null == expense) {
            throw new AccessException("L'id della spesa indicata non appartiene a te");
        }

        expencesRepository.delete(expense);
    }

    private List<Tags> convetToTags(List<TagJson> tags) {
        return tags.stream()
                .map(convertUtils::toTag)
                .collect(Collectors.toList());
    }

    private List<Tags> convertAndCreate(List<TagJson> tags, LoggedUser principal) {
        List<Tags> convertedTags = convetToTags(tags);
        return tagService.createIfNotExists(convertedTags, principal);
    }

}
