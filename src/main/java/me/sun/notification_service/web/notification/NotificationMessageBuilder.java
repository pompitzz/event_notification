package me.sun.notification_service.web.notification;

public interface NotificationMessageBuilder<R, S, F> {
    R successMessageBuild(S s);
    R failMessageBuild(F f);
}
