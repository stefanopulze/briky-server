package com.stefano.briky.configuration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DialogConfig {

    private String clientToken;
    private String developerToken;

    public DialogConfig(
            @Value("${dialog.api.client:}") String client,
            @Value("${dialog.api.developer:}") String developer) {
        this.clientToken = client;
        this.developerToken = developer;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getDeveloperToken() {
        return developerToken;
    }

}
