package me.sun.notification_service.core.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.NotificationEventQueryService;
import me.sun.notification_service.core.scheduler.starter.NotificationEventStarter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventScheduler {

    private final NotificationEventQueryService notificationEventQueryService;
    private final NotificationEventStarter notificationEventStarter;

//    @Async
//    @Scheduled(cron = "0 */1 * * * *")
//    public void minutelySchedule() {
//        log.info("Minutely Scheduled .... time: {}", LocalTime.now());
//    }
//
//    @Async
//    @Scheduled(cron = "*/3 * * * * *")
//    public void secondlySchedule() {
//        log.info("Three Secondly Scheduled .... time: {}", LocalTime.now());
//    }

    @Async
    @Scheduled(cron = "0 0 */1 * * *")
    public void hourlySchedule() {
        final List<NotificationEvent> notificationEvents = notificationEventQueryService.getEventsByNotificationTime(hourlyTime());
        for (NotificationEvent notificationEvent : notificationEvents) {
            notificationEventStarter.start(notificationEvent);
        }
    }

    private LocalTime hourlyTime() {
        final LocalTime time = LocalTime.now();
        return LocalTime.of(time.getHour(), 0);
    }

    public static void main(String[] args) {
        final LocalTime now = LocalTime.now();
        final int hour = now.getHour();
        System.out.println(LocalTime.of(hour, 0));
    }
}
