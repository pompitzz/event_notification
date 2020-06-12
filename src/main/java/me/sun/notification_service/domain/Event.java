package me.sun.notification_service.domain;

import me.sun.notification_service.schedule.NotificationInformation;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    public EventType getType() {
        return EventType.WEATHER;
    }

    public LocalDateTime makeTime() {
        return LocalDateTime.now();
    }

    public List<NotificationInformation> getNotificationInformation() {
        return null;
    }
}
