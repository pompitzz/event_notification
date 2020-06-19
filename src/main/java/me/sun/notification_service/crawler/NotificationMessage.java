package me.sun.notification_service.crawler;

import me.sun.notification_service.notification.kakao.KakaoNotificationPayload;
import me.sun.notification_service.notification.slack.dto.SlackNotificationPayload;
import me.sun.notification_service.notification.telegram.TelegramNotificationPayload;

public interface    NotificationMessage {
    SlackNotificationPayload toSlackMessage();
    KakaoNotificationPayload toKakaoMessage();
    TelegramNotificationPayload toTelegramMessage();
}
