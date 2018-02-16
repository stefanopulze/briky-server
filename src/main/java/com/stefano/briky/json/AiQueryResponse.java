package com.stefano.briky.json;

import com.stefano.briky.dialogflow.json.QueryResponse;

public class AiQueryResponse {

    private QueryResponse response;
    private ExpenseJson expense;

    public AiQueryResponse(QueryResponse response, ExpenseJson expense) {
        this.response = response;
        this.expense = expense;
    }

    public QueryResponse getResponse() {
        return response;
    }

    public void setResponse(QueryResponse response) {
        this.response = response;
    }

    public ExpenseJson getExpense() {
        return expense;
    }

    public void setExpense(ExpenseJson expense) {
        this.expense = expense;
    }
}
