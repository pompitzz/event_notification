package me.sun.notification_service.slack.dto;

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
