package com.slack.demo;

import com.slack.demo.prop.SlackTarget;
import com.slack.demo.domain.SlackNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SlackSendManagerTest {

    @Autowired
    private SlackSendManager slackSendManager;

    @Test
    void send() {
        //given
        SlackNotification slackNotification = new SlackNotification(SlackTarget.SP_DEV, "test");

        //when
        ResponseEntity<String> response = slackSendManager.send(slackNotification);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void parallelSend() {
        IntStream.range(0, 50)
                .parallel()
                .forEach(i -> {
                    //given
                    SlackNotification slackNotification = new SlackNotification(SlackTarget.SP_DEV, LocalDateTime.now().toString() + "test");

                    //when
                    System.out.println("===[" + LocalDateTime.now().toString() + "]" + "idx : " + i);
                    ResponseEntity<String> response = slackSendManager.send(slackNotification);

                    //then
                    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                });
    }
}