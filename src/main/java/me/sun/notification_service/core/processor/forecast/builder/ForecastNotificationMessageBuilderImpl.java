package me.sun.notification_service.core.processor.forecast.builder;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationInformation;
import me.sun.notification_service.web.notification.NotificationMessages;
import me.sun.notification_service.web.notification.slack.SlackArguments;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastNotificationMessageBuilderImpl implements ForecastNotificationMessageBuilder<NotificationMessages> {

    private final ForecastSlackMessageBuilder forecastSlackMessageBuilder;

    @Override
    public NotificationMessages successMessageBuild(ForecastResult forecastResult, NotificationInformation notificationInformation) {
        final SlackArguments slackArguments = forecastSlackMessageBuilder.successMessageBuild(forecastResult, notificationInformation);
        return NotificationMessages.builder()
                .slackArguments(slackArguments)
                .build();
    }

    @Override
    public NotificationMessages failMessageBuild(ForecastLocation forecastLocation, NotificationInformation notificationInformation) {
        final SlackArguments slackArguments = forecastSlackMessageBuilder.failMessageBuild(forecastLocation, notificationInformation);
        return NotificationMessages.builder()
                .slackArguments(slackArguments)
                .build();
    }
}
