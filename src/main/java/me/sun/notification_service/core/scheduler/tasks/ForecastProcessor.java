package me.sun.notification_service.core.scheduler.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.crawling.forecast.ForecastAdapter;
import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.domain.forecast.forecast.Forecast;
import me.sun.notification_service.core.domain.forecast.forecast.ForecastQueryService;
import me.sun.notification_service.core.service.ForecastMessageBuilder;
import me.sun.notification_service.core.service.model.ForecastMessage;
import me.sun.notification_service.web.notification.model.slack.SlackArguments;
import me.sun.notification_service.web.notification.model.slack.SlackNotificationService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ForecastProcessor {

    private final SlackNotificationService slackNotificationService;
    private final ForecastMessageBuilder forecastMessageBuilder;
    private final ForecastQueryService forecastQueryService;
    private final ForecastAdapter forecastAdapter;

    public void process(LocalTime time, Location location) {
        final List<ForecastResponse> forecastResponses = forecastAdapter.request(time, location, 40);
        final List<Forecast> todayForecasts = forecastQueryService.saveForecasts(forecastResponses);

        if (CollectionUtils.isEmpty(todayForecasts)) {
            log.error("정보를 받아오지 못헀습니다. Time: {}, Location: {}, x: {}, y:{}", time, location.getDescription(), location.getNx(), location.getNy());
            sendFailMessage(time ,location);
        }

        final List<Forecast> yesterdayForecasts = forecastQueryService.findYesterdayForecast(todayForecasts.get(0));

        final ForecastMessage forecastMessage = forecastMessageBuilder.build(yesterdayForecasts, todayForecasts);

        final SlackArguments slackArguments = SlackArguments.builder()
                .token("xoxb-1175038918580-1169073439443-DxGp4cjXFZHBEzrehGLoPr2P")
                .channel("C014S3QJQ4E")
                .text("TEXT")
                .attachments(forecastMessage.toAttachments())
                .build();

        final String result = slackNotificationService.sendMessage(slackArguments);
        log.info("### result: {}", result);
    }

    private void sendFailMessage(LocalTime time, Location location) {

        final String message = String.format("날씨조회에 실패했습니다. 위치: %s, 시간: %s", location.getDescription(), time);

        final SlackArguments slackArguments = SlackArguments.builder()
                .token("xoxb-1175038918580-1169073439443-DxGp4cjXFZHBEzrehGLoPr2P")
                .channel("C014S3QJQ4E")
                .text(message)
                .build();

        slackNotificationService.sendMessage(slackArguments);
    }

}
