package me.sun.notification_service.schedule;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.crawler.CrawlerFinderService;
import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.domain.Event;
import me.sun.notification_service.schedule.task.SchedulingTask;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final Scheduler scheduler;
    private final CrawlerFinderService crawlerFinderService;

    public void some(Event event) {
        final NotificationMessage message = crawlerFinderService.crawling(event.getType());
        final List<NotificationInformation> information = event.getNotificationInformation();
        scheduler.scheduling(new SchedulingTask(message, information), event.makeTime());
    }
}
