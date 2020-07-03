package me.sun.notification_service.core.processor.forecast.builder;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationMessageBuilder;
import me.sun.notification_service.web.notification.NotificationMessages;
import me.sun.notification_service.web.notification.slack.SlackArguments;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastNotificationMessageBuilder implements NotificationMessageBuilder<ForecastResult> {

    private final ForecastSlackMessageBuilder forecastSlackMessageBuilder;

    @Override
    public NotificationMessages build(ForecastResult forecastResult) {
        final SlackArguments slackArguments = forecastSlackMessageBuilder.build(forecastResult);

        return NotificationMessages.builder()
                .slackArguments(slackArguments)
                .build();

    }
}
