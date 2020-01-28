package com.slack.demo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.demo.prop.SlackTarget;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SlackNotification {
    private String url;
    private String channel;
    private SlackMessage slackMessage;

    public SlackNotification(String url, String channel, String message) {
        this.url = url;
        this.channel = channel;
        this.slackMessage = SlackMessage.builder().text(message).build();
    }

    public SlackNotification(SlackTarget target, String message) {
        this.url = target.getWebHookUrl();
        this.channel = target.getChannel();
        this.slackMessage = SlackMessage.builder().text(message).build();
    }

    public ResponseEntity<String> send(RestTemplate restTemplate, ObjectMapper objMapper) {
        return restTemplate.postForEntity(url, slackMessage.toJson(objMapper), String.class);
    }
}
