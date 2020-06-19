package me.sun.notification_service;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.notification.slack.SlackNotificationService;
import me.sun.notification_service.notification.slack.dto.Attachment;
import me.sun.notification_service.notification.slack.dto.Field;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class NotificationRunner implements ApplicationRunner {
    final String URL = "https://hooks.slack.com/services/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";

    private final SlackNotificationService slackNotificationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final Attachment attachment = Attachment.builder()
                .pretext("2020-06-19 오늘의 날씨")
                .title("서울특별시 강동구 천호동")
                .title_link("http://naver.com")
                .text("강수확률 : 50% (비가 올것같네요)")
                .fields(Arrays.asList(
                        Field.builder().title("현재 온도").value("30°C (어제보다 3도 음)").build(),
                        Field.builder().title("낮 최고기온").value("35°C (어제보다 4도 높음)").build()
                ))
                .color("#36a64f")
                .build();

        System.out.println("###" + slackNotificationService.sendMessage(URL, attachment));
    }
}
