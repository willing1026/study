package com.slack.demo.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class SlackMessage {
    private String text;

    @Builder
    public SlackMessage(String text) {
        this.text = text;
    }

    public Object toJson(ObjectMapper objMapper) {
        try {
            objMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
            return objMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("Occur JsonProcessingException: {}", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
