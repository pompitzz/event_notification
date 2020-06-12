package me.sun.notification_service.crawler;

import me.sun.notification_service.domain.EventType;

public interface Crawler {
    NotificationMessage crawling();

    boolean isType(EventType type);
}
