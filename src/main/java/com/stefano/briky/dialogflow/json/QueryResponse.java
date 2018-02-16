package com.stefano.briky.dialogflow.json;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class QueryResponse {

    private String id;
    private LocalDateTime timestamp;
    private String lang;
    private Map<String, String> parameters;
    private String resolvedQuery;
    private String sessionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public void setResolvedQuery(String resolvedQuery) {
        this.resolvedQuery = resolvedQuery;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

/*
{
    "id": "b170347f-144d-4250-95c7-6ec8083a42b9",
    "timestamp": "2018-02-16T13:40:39.016Z",
    "lang": "it",
    "result": {
        "source": "agent",
        "resolvedQuery": "Oggi ho speso 10 euro in carne",
        "speech": "",
        "action": "",
        "parameters": {
            "amount": "10",
            "briky-tag": "carne",
            "date": "2018-02-16"
        },
        "metadata": {
            "inputContexts": [],
            "outputContexts": [],
            "intentName": "expense-intent",
            "intentId": "102f7293-f6a7-4465-9bf1-f1bea566bd91",
            "webhookUsed": "false",
            "webhookForSlotFillingUsed": "false",
            "contexts": []
        },
        "score": 1
    },
    "status": {
        "code": 200,
        "errorType": "success",
        "webhookTimedOut": false
    },
    "sessionId": "123"
}
 */