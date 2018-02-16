package com.stefano.briky.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefano.briky.configuration.config.DialogConfig;
import com.stefano.briky.dialogflow.DialogFlow;
import com.stefano.briky.dialogflow.DialogFlowClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DialogFlowConfiguration {

    @Autowired
    DialogConfig config;

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public DialogFlow dialogFlow() {

        return new DialogFlowClient(config, objectMapper);

    }

}
