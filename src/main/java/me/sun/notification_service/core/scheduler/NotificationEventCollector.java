package me.sun.notification_service.core.scheduler;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.NotificationEventQueryService;
import me.sun.notification_service.core.scheduler.starter.NotificationEventStarter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NotificationEventCollector {

    private final NotificationEventQueryService notificationEventQueryService;
    private final NotificationEventStarter notificationEventStarter;

    public void someStarter() {
        final List<NotificationEvent> notificationEvents = notificationEventQueryService.getNotificationEvents();
        for (NotificationEvent notificationEvent : notificationEvents) {
            notificationEventStarter.start(notificationEvent);
        }
    }
}
