package me.sun.notification_service.core.domain.notification_event.repository;

import me.sun.notification_service.core.domain.notification_event.NotificationEvent;

import java.time.LocalTime;
import java.util.List;

public interface NotificationEventRepositoryCustom {
    List<NotificationEvent> findByEventTime(LocalTime eventTime);
}
