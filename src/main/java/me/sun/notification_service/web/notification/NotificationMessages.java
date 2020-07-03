package me.sun.notification_service.web.notification.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.web.notification.model.telegram.TelegramArguments;
import me.sun.notification_service.web.notification.model.slack.SlackArguments;

@Builder
@AllArgsConstructor
public class NotificationMessages {
    private SlackArguments slackArguments;
    private TelegramArguments telegramArguments;

    public SlackArguments toSlack() {
        return this.slackArguments;
    }

    public TelegramArguments toTelegram() {
        return this.telegramArguments;
    }
}
