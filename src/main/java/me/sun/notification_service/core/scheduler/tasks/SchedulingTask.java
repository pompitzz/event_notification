package me.sun.notification_service.core.scheduler.tasks;

import lombok.AllArgsConstructor;
import me.sun.notification_service.web.notification.NotificationMessage;
import me.sun.notification_service.web.notification.NotificationInformation;

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
