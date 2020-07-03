package me.sun.notification_service.core.crawling.forecast;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequest;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastTime;
import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponseDto;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
import me.sun.notification_service.core.crawling.forecast.model.ForecastRequestInfo;
import me.sun.notification_service.infrastructure.utils.UrlUtils;
import me.sun.notification_service.infrastructure.wrapper.RestTemplateAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ForecastAdapter {

    private final ForecastProperty forecastProperty;
    private final RestTemplateAdapter restTemplateAdapter;

    public List<ForecastResponse> request(ForecastRequestInfo forecastRequestInfo) {
        final ForecastRequest forecastRequest = ForecastRequest.builder()
                .serviceKey(forecastProperty.getServiceKey())
                .base_date(findDate())
                .base_time(findTime(forecastRequestInfo.getNotificationTime()))
                .nx(forecastRequestInfo.getNx())
                .ny(forecastRequestInfo.getNy())
                .numOfRows(forecastRequestInfo.getSize())
                .pageNo(1)
                .dataType("JSON")
                .build();

        String url = UrlUtils.buildUrl(forecastProperty.getBaseUrl(), forecastRequest, false);

        ForecastResponseDto forecastResponseDto = restTemplateAdapter.requestAndGetBody(url, ForecastResponseDto.class);
        return Objects.requireNonNull(forecastResponseDto).getForecast();
    }

    private static String findDate() {
        LocalDate date = LocalDate.now();
        return StringUtils.replace(date.toString(), "-", "");
    }

    private String findTime(LocalTime time) {
        return ForecastTime.findOptimalForecastTime(time).timeString();
    }
}