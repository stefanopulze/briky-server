package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.TagDetailJson;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.model.Tags;
import com.stefano.briky.repository.TagRepository;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public TagJson createTag(@AuthenticationPrincipal LoggedUser principal, @RequestBody TagJson json) {
        Tags tag = modelMapper.map(json, Tags.class);

        tag = tagService.createIfNotExists(tag, principal.getId());

        return modelMapper.map(tag, TagJson.class);
    }

    @RequestMapping(value = "/tags")
    public List<Tags> mostUsedTag(@AuthenticationPrincipal LoggedUser user) {
        return tagRepository.findByUserIdOrderByName(user.getId());
    }

    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
    public TagDetailJson createTag(@AuthenticationPrincipal LoggedUser principal, @PathVariable int id) {
        Tags tag = tagRepository.getOne(id);

        List<Expenses> expenses = expenseService.findLastByTagId(5, id);

        TagDetailJson json = modelMapper.map(tag, TagDetailJson.class);
        json.setUsedCount(tagService.countTagUsage(id));

        json.setExpenses(expenses.stream().map(expense -> modelMapper.map(expense, ExpenseJson.class)).collect(Collectors.toList()));


        return json;
    }
}
