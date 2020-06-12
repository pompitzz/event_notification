package me.sun.notification_service.schedule;

import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.domain.Member;
import me.sun.notification_service.notification.NotificationService;

public class NotificationInformation {
    private Member member;
    private NotificationService notificationService;

    public String destination() {
        return member.url();
    }

    public void doNotify(NotificationMessage notificationMessage) {
        notificationService.sendMessage(this, notificationMessage);
    }
}
