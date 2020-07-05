package me.sun.notification_service.core.processor.forecast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.crawling.forecast.ForecastAdapter;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestInfo;
import me.sun.notification_service.core.domain.forecast.forecast.Forecast;
import me.sun.notification_service.core.domain.forecast.forecast.ForecastQueryService;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.processor.forecast.builder.ForecastNotificationMessageBuilderImpl;
import me.sun.notification_service.core.processor.forecast.builder.ForecastResultBuilder;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationInformation;
import me.sun.notification_service.web.notification.NotificationServiceAdapter;
import me.sun.notification_service.web.notification.NotificationMessages;
import me.sun.notification_service.web.notification.slack.SlackArguments;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastProcessor {

    private final ForecastAdapter forecastAdapter;
    private final ForecastQueryService forecastQueryService;
    private final ForecastResultBuilder forecastResultBuilder;
    private final ForecastNotificationMessageBuilderImpl forecastNotificationMessageBuilderImpl;
    private final NotificationServiceAdapter notificationServiceAdapter;

    public void process(ForecastRequestInfo forecastRequestInfo, ForecastLocation forecastLocation, NotificationInformation notificationInformation) {
        final List<ForecastResponse> forecastResponses = forecastAdapter.requestTodayForecast(forecastRequestInfo);
        final List<Forecast> todayForecasts = forecastQueryService.saveForecasts(forecastResponses, forecastLocation);

        // TODO fallback 제대로 구현하기
        if (CollectionUtils.isEmpty(todayForecasts)) {
            sendFailMessage(forecastRequestInfo);
        }

        final List<Forecast> yesterdayForecasts = forecastQueryService.findYesterdayForecast(todayForecasts.get(0));

        final ForecastResult forecastResult = forecastResultBuilder.build(yesterdayForecasts, todayForecasts);

        // TODO 변경
        final NotificationMessages notificationMessages = forecastNotificationMessageBuilderImpl.successMessageBuild(forecastResult, notificationInformation);

        final String result = notificationServiceAdapter.sendMessage(notificationMessages, forecastRequestInfo.getNotificationType());

        // TODO 결과 확인 후 변경
        log.info("### result: {}", result);
    }

    private void sendFailMessage(ForecastRequestInfo forecastRequestInfo) {

        final String locationDescription = forecastRequestInfo.getLocationDescription();
        final String message = String.format("날씨조회에 실패했습니다. 위치: %s", locationDescription);

        final SlackArguments slackArguments = SlackArguments.builder()
                .token("xoxb-1175038918580-1169073439443-DxGp4cjXFZHBEzrehGLoPr2P")
                .channel("C014S3QJQ4E")
                .text(message)
                .build();

//        notificationService.sendMessage(NotificationMessages.builder().slackArguments(slackArguments).build());
    }

}
