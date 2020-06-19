package me.sun.notification_service.crawler;

import me.sun.notification_service.domain.notification_event.EventType;

public interface Adapter {
    boolean isType(EventType type);
}
