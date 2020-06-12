package me.sun.notification_service.schedule.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.schedule.NotificationInformation;

import java.util.List;

@AllArgsConstructor
public class SchedulingTask implements Runnable {

    private NotificationMessage weatherNotificationMessage;
    private List<NotificationInformation> notificationInformation;

    @Override
    public void run() {
        for (NotificationInformation information : notificationInformation) {
            information.doNotify(weatherNotificationMessage);
        }
    }
}
