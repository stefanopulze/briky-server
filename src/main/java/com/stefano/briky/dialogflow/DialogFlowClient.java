package com.stefano.briky.dialogflow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefano.briky.configuration.config.DialogConfig;
import com.stefano.briky.dialogflow.json.QueryResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DialogFlowClient implements DialogFlow {

    private static final String URL = "https://api.dialogflow.com/v1";

    private DialogConfig config;
    private ObjectMapper mapper;
    private HttpClient http;


    public DialogFlowClient(DialogConfig config, ObjectMapper mapper) {
        this.http = HttpClients.createMinimal();
        this.config = config;
        this.mapper = mapper;
    }

    @Override
    public QueryResponse query(String query) throws DialogFlowException {
        HttpPost post = new HttpPost(URL + "/query");
        post.addHeader("Authorization", "Bearer " + config.getClientToken());
        post.addHeader("Content-Type", "application/json");

        Map<String, String> body = new HashMap<>();
        body.put("lang", "it_IT");
        body.put("sessionId", "1");
        body.put("query", query);

        try {
            post.setEntity(new StringEntity(mapper.writeValueAsString(body)));

            HttpResponse dialogResponse = http.execute(post);

            // Debug
            String responseText = EntityUtils.toString(dialogResponse.getEntity());
            JsonNode json = mapper.readTree(responseText);
            System.out.println(responseText);

            // Prod
            //JsonNode json = mapper.readTree(response.getEntity().getContent());

            int status = json.at("/status/code").asInt();

            if(status != 200) {
                String errorType = json.at("/status/errorType").asText();
                String errorDetails = json.at("/status/errorDetails").asText();

                throw new DialogFlowException(status, errorType, errorDetails);
            }

            QueryResponse response = mapper.readValue(responseText, QueryResponse.class);
            response.setResolvedQuery(json.at("/result/resolvedQuery").asText());
            response.setParameters(mapper.treeToValue(
                    json.at("/result/parameters"),
                    HashMap.class));

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DialogFlowException(500, "server_error", "IOException");
        }
    }
}
