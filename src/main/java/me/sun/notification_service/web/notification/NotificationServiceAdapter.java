package me.sun.notification_service.web.notification;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.web.notification.model.common.NotificationMessages;
import me.sun.notification_service.web.notification.model.common.NotificationType;
import me.sun.notification_service.web.notification.model.slack.SlackNotificationService;
import me.sun.notification_service.web.notification.model.telegram.TelegramNotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceAdapter {
    private final SlackNotificationService slackNotificationService;
    private final TelegramNotificationService telegramNotificationService;

    public String sendMessage(NotificationMessages notificationMessages, NotificationType notificationType) {
        switch (notificationType) {
            case SLACK:
                return slackNotificationService.sendMessage(notificationMessages);
            case TELEGRAM:
                return telegramNotificationService.sendMessage(notificationMessages);
            default:
                throw new AssertionError("It can be occurred");
        }
    }
}
