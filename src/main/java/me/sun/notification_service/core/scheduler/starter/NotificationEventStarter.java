package me.sun.notification_service.core.scheduler.starter;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.notification_event.EventType;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.web.notification.NotificationType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventStarter {

    private final ForecastEventStarter forecastEventStarter;
    private final KorailEventStarter korailEventStarter;

    public void start(NotificationEvent notificationEvent) {
        final EventType eventType = notificationEvent.getEventType();
        final Long targetTableId = notificationEvent.getEventTargetTableId();
        final NotificationType notificationType = notificationEvent.getNotificationType();
        switch (eventType) {
            case FORECAST:
                forecastEventStarter.start(notificationEvent);
                break;
            case KORAIL:
                korailEventStarter.start(targetTableId, notificationType);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
    }
}
