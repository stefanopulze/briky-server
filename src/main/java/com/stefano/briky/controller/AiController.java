package com.stefano.briky.controller;

import com.stefano.briky.configuration.security.LoggedUser;
import com.stefano.briky.dialogflow.DialogFlow;
import com.stefano.briky.dialogflow.DialogFlowException;
import com.stefano.briky.dialogflow.json.QueryResponse;
import com.stefano.briky.json.AiQuery;
import com.stefano.briky.json.AiQueryResponse;
import com.stefano.briky.json.ExpenseJson;
import com.stefano.briky.json.TagJson;
import com.stefano.briky.model.Expenses;
import com.stefano.briky.service.ExpenseService;
import com.stefano.briky.utils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class AiController {

    @Autowired
    @Lazy
    DialogFlow dialogFlow;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ConvertUtils convertUtils;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/ai/query", method = RequestMethod.POST)
    public AiQueryResponse queryDialogFlow(
            @AuthenticationPrincipal LoggedUser principal,
            @RequestBody AiQuery query) throws DialogFlowException {

        QueryResponse response = dialogFlow.query(query.getQuery());

        if(StringUtils.isBlank(response.getParameters().get("amount"))) {
            throw new DialogFlowException(500, "ai_error", "AI dont recognize amout value");
        }

        if(StringUtils.isBlank(response.getParameters().get("briky-tag"))) {
            throw new DialogFlowException(500, "ai_error", "AI dont recognize tag");
        }

        Double amount = Double.parseDouble(response.getParameters().get("amount"));
        String tag = response.getParameters().get("briky-tag");

        Expenses expense = convertUtils.toExpense(new ExpenseJson(amount, tag));

        String dateString = response.getParameters().get("date");
        if (StringUtils.isNotEmpty(dateString)) {
            LocalDate date = LocalDate.parse(dateString);
            if (date != null) {
                expense.setCreatedAt(date.atTime(LocalTime.now()));
            }
        }

        expenseService.create(expense);

        return new AiQueryResponse(
                response,
                modelMapper.map(expense, ExpenseJson.class));
    }

}
