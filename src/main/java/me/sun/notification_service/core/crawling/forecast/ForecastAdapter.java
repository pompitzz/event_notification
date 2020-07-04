package me.sun.notification_service.core.crawling.forecast;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestInfo;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestParameter;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestTime;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponseWrapper;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
import me.sun.notification_service.infrastructure.utils.UrlUtils;
import me.sun.notification_service.infrastructure.wrapper.RestTemplateAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ForecastAdapter {

    private final static int DEFAULT_FETCH_SIZE = 100;
    private final static int DEFAULT_PAGE_NUMBER = 1;
    private final static String DEFAULT_DATA_TYPE = "JSON";

    private final ForecastProperty forecastProperty;
    private final RestTemplateAdapter restTemplateAdapter;

    public List<ForecastResponse> request(ForecastRequestInfo forecastRequestInfo) {
        String url = buildUrl(forecastRequestInfo);
        ForecastResponseWrapper forecastResponseWrapper = restTemplateAdapter.requestAndGetBody(url, ForecastResponseWrapper.class);
        return Objects.requireNonNull(forecastResponseWrapper).getForecast();
    }

    private String buildUrl(ForecastRequestInfo forecastRequestInfo) {
        final LocalDateTime optimalLocalDateTime = findOptimalLocalDateTime();
        final ForecastRequestParameter forecastRequestParameter = buildParameter(forecastRequestInfo, optimalLocalDateTime);
        return UrlUtils.buildUrl(forecastProperty.getBaseUrl(), forecastRequestParameter, false);
    }

    private ForecastRequestParameter buildParameter(ForecastRequestInfo forecastRequestInfo, LocalDateTime localDateTime) {
        return ForecastRequestParameter.builder()
                .serviceKey(forecastProperty.getServiceKey())
                .base_date(parse(localDateTime.toLocalDate()))
                .base_time(parse(localDateTime.toLocalTime()))
                .nx(forecastRequestInfo.getLocationNx())
                .ny(forecastRequestInfo.getLocationNy())
                .numOfRows(DEFAULT_FETCH_SIZE)
                .pageNo(DEFAULT_PAGE_NUMBER)
                .dataType(DEFAULT_DATA_TYPE)
                .build();
    }

    private LocalDateTime findOptimalLocalDateTime() {
        return ForecastRequestTime.findOptimalForecastLocalDateTime(LocalDate.now(), LocalTime.now());
    }

    private String parse(LocalDate date) {
        return StringUtils.replace(date.toString(), "-", "");
    }

    private String parse(LocalTime time) {
        return StringUtils.replace(time.toString(), ":", "");
    }
}