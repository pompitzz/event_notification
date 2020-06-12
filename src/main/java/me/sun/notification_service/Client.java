package me.sun.notification_service;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.notification.slack.SlackNotificationService;
import me.sun.notification_service.notification.slack.dto.Attachment;
import me.sun.notification_service.notification.slack.dto.Field;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Arrays;

@RequiredArgsConstructor
public class Client implements ApplicationRunner {
    final String URL = "https://hooks.slack.com/services/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";

    private final SlackNotificationService slackNotificationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final Attachment attachment = Attachment.builder()
                .text("Test")
                .fields(Arrays.asList(
                        Field.builder().title("Hello Field").value("I`m Value").build(),
                        Field.builder().title("Hello Field").value("I`m Value").isShort(true).build(),
                        Field.builder().title("Hello Field").value("I`m Value").isShort(true).build(),
                        Field.builder().title("Hello Field").value("I`m Value").isShort(true).build()
                ))
                .build();

        slackNotificationService.sendMessage(URL, attachment);
    }
}
