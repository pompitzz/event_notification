package me.sun.notification_service.core.processor.forecast.builder;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationMessages;
import me.sun.notification_service.web.notification.slack.SlackArguments;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastNotificationMessageBuilderImpl implements ForecastNotificationMessageBuilder<NotificationMessages> {

    private final ForecastSlackMessageBuilder forecastSlackMessageBuilder;

    @Override
    public NotificationMessages successMessageBuild(ForecastResult forecastResult) {
        final SlackArguments slackArguments = forecastSlackMessageBuilder.successMessageBuild(forecastResult);
        return NotificationMessages.builder()
                .slackArguments(slackArguments)
                .build();
    }

    @Override
    public NotificationMessages failMessageBuild(ForecastLocation forecastLocation) {
        final SlackArguments slackArguments = forecastSlackMessageBuilder.failMessageBuild(forecastLocation);
        return NotificationMessages.builder()
                .slackArguments(slackArguments)
                .build();
    }
}
