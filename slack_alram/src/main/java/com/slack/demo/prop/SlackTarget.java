package com.slack.demo.prop;

import lombok.Getter;

@Getter
public enum SlackTarget {
    /**
     * naming rule
     * = WORKSPACE_CHANNEL
     */
    SP_DEV("WebHook URL","channel");

    private final String webHookUrl;
    private final String channel;

    SlackTarget(String webHookUrl, String channel) {
        this.webHookUrl = webHookUrl;
        this.channel = channel;
    }
}

