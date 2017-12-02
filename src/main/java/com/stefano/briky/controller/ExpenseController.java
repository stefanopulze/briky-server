package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.ExpenceJson;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.ExpencesRepository;
import com.stefano.briky.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {

    @Autowired
    ExpencesRepository expencesRepository;

    @Autowired
    TagService tagService;

    @Autowired
    ModelMapper modelMapper;


    @RequestMapping(value = "/expense", method = RequestMethod.POST)
    public Expenses createExpense(@AuthenticationPrincipal LoggedUser principal, @RequestBody ExpenceJson json) {
        List<Tags> checkedTags = convertAndCreate(json.getTags(), principal);

        Expenses expense = new Expenses(json);
        expense.setUserId(principal.getId());
        expense.setTags(checkedTags);

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

        List<Tags> checkedTags = convertAndCreate(json.getTags(), principal);

        expense.setValue(json.getValue());
        expense.setLatitude(json.getLatitude());
        expense.setLongitude(json.getLongitude());
        expense.setAccuracy(json.getAccuracy());
        expense.setUpdatedAt(new Date());
        expense.setDescription(json.getDescription());
        expense.setTags(checkedTags);

        expencesRepository.save(expense);

        return expense;
    }

    private List<Tags> convetToTags(List<TagJson> tags) {
        return tags.stream()
                .map(tag -> modelMapper.map(tag, Tags.class))
                .collect(Collectors.toList());
    }

    private List<Tags> convertAndCreate(List<TagJson> tags, LoggedUser principal) {
        List<Tags> convertedTags = convetToTags(tags);
        return tagService.createIfNotExists(convertedTags, principal);
    }


    public void aa() {

    }
}
