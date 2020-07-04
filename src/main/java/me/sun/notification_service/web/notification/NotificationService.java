package me.sun.notification_service.web.notification;

public interface NotificationService<T> {
    String sendMessage(T t);
}
