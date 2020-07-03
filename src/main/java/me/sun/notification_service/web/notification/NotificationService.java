package me.sun.notification_service.web.notification;

import me.sun.notification_service.web.notification.model.common.NotificationMessages;

public interface NotificationService {
    String sendMessage(NotificationMessages notificationMessages);
}
