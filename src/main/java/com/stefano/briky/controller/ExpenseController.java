package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.ExpenceJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.repository.ExpencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ExpenseController {

    @Autowired
    ExpencesRepository expencesRepository;


    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    public Expenses createExpense(@AuthenticationPrincipal LoggedUser principal, @RequestBody ExpenceJson json) {
        Expenses expense = new Expenses(json);
        expense.setUserId(principal.getId());

        expencesRepository.save(expense);

        return expense;
    }

    @RequestMapping(value = "/expense/{id}", method = RequestMethod.PUT)
    public Expenses updateExpense(@AuthenticationPrincipal LoggedUser principal,
                                  @RequestBody ExpenceJson json,
                                  @PathVariable("id") int expenseId) throws AccessException {

        Expenses expense = expencesRepository.findByIdAndUserId(expenseId, principal.getId());

        if (null == expense) {
            // TODO lanciare eccezione
            throw new AccessException("aaa");
        }

        expense.setValue(json.getValue());
        expense.setLatitude(json.getLatitude());
        expense.setLongitude(json.getLongitude());
        expense.setAccuracy(json.getAccuracy());
        expense.setUpdatedAt(new Date());

        expencesRepository.save(expense);

        return expense;
    }


    public void aa() {

    }
}
