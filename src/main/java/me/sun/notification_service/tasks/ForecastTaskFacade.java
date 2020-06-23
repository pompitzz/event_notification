package me.sun.notification_service.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.crawler.forecast.ForecastAdapter;
import me.sun.notification_service.crawler.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.domain.forecast.Forecast;
import me.sun.notification_service.notification.slack.SlackNotificationService;
import me.sun.notification_service.service.message.ForecastMessageBuilder;
import me.sun.notification_service.service.message.model.ForecastMessage;
import me.sun.notification_service.service.query.ForecastQueryService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastTaskFacade {
    final String URL = "https://hooks.slack.com/services/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";

    private final SlackNotificationService slackNotificationService;
    private final ForecastMessageBuilder forecastMessageBuilder;
    private final ForecastQueryService forecastQueryService;
    private final ForecastAdapter forecastAdapter;

    public void run(LocalTime time, String nx, String ny) {
        final List<ForecastResponse> forecastResponses = forecastAdapter.request(time, nx, ny, 40);
        final List<Forecast> todayForecasts = forecastQueryService.saveForecasts(forecastResponses);

        if (CollectionUtils.isEmpty(todayForecasts)) {
            log.error("정보를 받아오지 못헀습니다. Time: {}, X: {}, Y:{}", time, nx, ny);
        }

        final List<Forecast> yesterdayForecasts = forecastQueryService.findYesterdayForecast(todayForecasts.get(0));

        final ForecastMessage forecastMessage = forecastMessageBuilder.build(yesterdayForecasts, todayForecasts);

        slackNotificationService.sendMessage(URL, forecastMessage.toAttachments());
    }

}
