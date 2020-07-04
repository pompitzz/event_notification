package me.sun.notification_service.web.notification.telegram;

import me.sun.notification_service.web.notification.NotificationService;
import me.sun.notification_service.web.notification.NotificationMessages;
import org.springframework.stereotype.Service;

@Service
public class TelegramNotificationService implements NotificationService<TelegramArguments> {
    @Override
    public String sendMessage(TelegramArguments telegramArguments) {
        return null;
    }
}
