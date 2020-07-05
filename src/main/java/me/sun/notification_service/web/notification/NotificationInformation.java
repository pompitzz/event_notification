package me.sun.notification_service.web.notification;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationInformation {
    private String slackToken;
}
