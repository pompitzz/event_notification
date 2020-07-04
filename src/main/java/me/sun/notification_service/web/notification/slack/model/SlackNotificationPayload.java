package me.sun.notification_service.web.notification.slack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class SlackNotificationPayload {
    private List<Attachment> attachments;
}
