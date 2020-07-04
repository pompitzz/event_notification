package me.sun.notification_service.web.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.sun.notification_service.web.notification.telegram.TelegramArguments;
import me.sun.notification_service.web.notification.slack.SlackArguments;

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
