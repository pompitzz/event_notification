package me.sun.notification_service.crawler.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.notification.kakao.KakaoNotificationPayload;
import me.sun.notification_service.notification.slack.dto.SlackNotificationPayload;
import me.sun.notification_service.notification.telegram.TelegramNotificationPayload;

@Builder
@AllArgsConstructor
public class WeatherNotificationMessage implements NotificationMessage {

    private String day;
    private String temperature;
    private String humidity;
    private String rainPercent;


    @Override
    public SlackNotificationPayload toSlackMessage() {
        return null;
    }

    @Override
    public KakaoNotificationPayload toKakaoMessage() {
        return null;
    }

    @Override
    public TelegramNotificationPayload toTelegramMessage() {
        return null;
    }
}
