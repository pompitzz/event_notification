package me.sun.notification_service.web.notification;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.web.notification.slack.SlackNotificationService;
import me.sun.notification_service.web.notification.telegram.TelegramNotificationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceAdapter {

    private final SlackNotificationService slackNotificationService;
    private final TelegramNotificationService telegramNotificationService;

    public String sendMessage(NotificationMessages notificationMessages, NotificationType notificationType) {
        switch (notificationType) {
            case SLACK:
                return slackNotificationService.sendMessage(notificationMessages.toSlack());
            case TELEGRAM:
                return telegramNotificationService.sendMessage(notificationMessages.toTelegram());
            default:
                throw new AssertionError("It can be occurred");
        }
    }
}
