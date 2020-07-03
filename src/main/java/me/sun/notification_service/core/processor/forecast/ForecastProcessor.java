package me.sun.notification_service.core.scheduler.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.crawling.forecast.ForecastAdapter;
import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.model.ForecastRequestInfo;
import me.sun.notification_service.core.domain.forecast.forecast.Forecast;
import me.sun.notification_service.core.domain.forecast.forecast.ForecastQueryService;
import me.sun.notification_service.core.service.builder.ForecastNotificationMessageBuilder;
import me.sun.notification_service.core.service.builder.ForecastResultBuilder;
import me.sun.notification_service.core.service.builder.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationServiceAdapter;
import me.sun.notification_service.web.notification.model.common.NotificationMessages;
import me.sun.notification_service.web.notification.model.common.NotificationType;
import me.sun.notification_service.web.notification.model.slack.SlackArguments;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastProcessor{

    private final ForecastAdapter forecastAdapter;
    private final ForecastQueryService forecastQueryService;
    private final ForecastResultBuilder forecastResultBuilder;
    private final ForecastNotificationMessageBuilder forecastNotificationMessageBuilder;
    private final NotificationServiceAdapter notificationServiceAdapter;

    public void process(ForecastRequestInfo forecastRequestInfo) {
        final List<ForecastResponse> forecastResponses = forecastAdapter.request(forecastRequestInfo);
        final List<Forecast> todayForecasts = forecastQueryService.saveForecasts(forecastResponses);

        if (CollectionUtils.isEmpty(todayForecasts)) {
            sendFailMessage(forecastRequestInfo);
        }

        final List<Forecast> yesterdayForecasts = forecastQueryService.findYesterdayForecast(todayForecasts.get(0));

        final ForecastResult forecastResult = forecastResultBuilder.build(yesterdayForecasts, todayForecasts);

        // TODO 변경
        final NotificationMessages notificationMessages = forecastNotificationMessageBuilder.build(forecastResult);

        // TODO 타입은 이벤트 파라미터로 받아오
        final String result = notificationServiceAdapter.sendMessage(notificationMessages,forecastRequestInfo.getNotificationType());

        // TODO 결과 확인 후 변경
        log.info("### result: {}", result);
    }

    private void sendFailMessage(ForecastRequestInfo forecastRequestInfo) {

        final String message = String.format("날씨조회에 실패했습니다. 위치: %s, 시간: %s", forecastRequestInfo.getDescription(), forecastRequestInfo.getNotificationTime());

        final SlackArguments slackArguments = SlackArguments.builder()
                .token("xoxb-1175038918580-1169073439443-DxGp4cjXFZHBEzrehGLoPr2P")
                .channel("C014S3QJQ4E")
                .text(message)
                .build();

//        notificationService.sendMessage(NotificationMessages.builder().slackArguments(slackArguments).build());
    }

}
