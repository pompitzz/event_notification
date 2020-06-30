package me.sun.notification_service.web.notification;

import me.sun.notification_service.web.notification.model.kakao.KakaoNotificationPayload;
import me.sun.notification_service.web.notification.model.slack.dto.SlackNotificationPayload;
import me.sun.notification_service.web.notification.model.telegram.TelegramNotificationPayload;

public interface    NotificationMessage {
    SlackNotificationPayload toSlackMessage();
    KakaoNotificationPayload toKakaoMessage();
    TelegramNotificationPayload toTelegramMessage();
}
