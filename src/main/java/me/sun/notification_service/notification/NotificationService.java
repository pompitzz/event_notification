package me.sun.notification_service.notification;

import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.schedule.NotificationInformation;

public interface NotificationService {
    void sendMessage(NotificationInformation notificationInformation, NotificationMessage notificationMessage);
}
