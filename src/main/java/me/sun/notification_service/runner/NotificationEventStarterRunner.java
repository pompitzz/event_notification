package me.sun.notification_service.runner;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.NotificationEventQueryService;
import me.sun.notification_service.core.domain.notification_event.repository.NotificationEventRepository;
import me.sun.notification_service.core.scheduler.starter.NotificationEventStarter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class NotificationEventStarterRunner implements ApplicationRunner {

    private final NotificationEventStarter notificationEventStarter;
    private final NotificationEventQueryService notificationEventQueryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final List<NotificationEvent> notificationEvents = notificationEventQueryService.getNotificationEvents();
        for (NotificationEvent notificationEvent : notificationEvents) {
            notificationEventStarter.start(notificationEvent);
            break;
        }
    }
}
