package me.sun.notification_service.web.notification;

import me.sun.notification_service.core.domain.member.Member;

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
