package me.sun.notification_service.schedule;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.crawler.AdapterFinder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final Scheduler scheduler;
    private final AdapterFinder adapterFinder;

//    public void some(Event event) {
//        final NotificationMessage message = crawlerFinderService.crawling(event.getType());
//        final List<NotificationInformation> information = event.getNotificationInformation();
//        scheduler.scheduling(new SchedulingTask(message, information), event.makeTime());
//    }
}
