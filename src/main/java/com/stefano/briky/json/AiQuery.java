package com.stefano.briky.json;

public class AiQuery {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "AiQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
