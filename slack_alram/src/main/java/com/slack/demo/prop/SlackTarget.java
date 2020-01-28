package com.slack.demo.prop;

import lombok.Getter;

@Getter
public enum SlackTarget {
    /**
     * naming rule
     * = WORKSPACE_CHANNEL
     */
    SP_DEV("https://hooks.slack.com/services/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","dev"),
    SP_DEV2("https://hooks.slack.com/services/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","dev2");

    private final String webHookUrl;
    private final String channel;

    SlackTarget(String webHookUrl, String channel) {
        this.webHookUrl = webHookUrl;
        this.channel = channel;
    }
}

