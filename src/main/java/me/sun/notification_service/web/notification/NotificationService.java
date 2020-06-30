package me.sun.notification_service.web.notification;

public interface NotificationService {
    void sendMessage(NotificationInformation notificationInformation, NotificationMessage notificationMessage);
}
