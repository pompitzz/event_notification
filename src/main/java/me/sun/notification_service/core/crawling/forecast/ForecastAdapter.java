package me.sun.notification_service.core.crawling.forecast;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestInfo;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestParameter;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestTime;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponseWrapper;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
import me.sun.notification_service.infrastructure.utils.StreamUtils;
import me.sun.notification_service.infrastructure.utils.UrlUtils;
import me.sun.notification_service.infrastructure.wrapper.RestTemplateAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastAdapter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private final static int DEFAULT_FETCH_SIZE = 50;
    private final static int DEFAULT_PAGE_NUMBER = 1;
    private final static String DEFAULT_DATA_TYPE = "JSON";

    private final ForecastProperty forecastProperty;
    private final RestTemplateAdapter restTemplateAdapter;

    public List<ForecastResponse> requestTodayForecast(ForecastRequestInfo forecastRequestInfo) {
        final LocalDateTime optimalLocalDateTime = findOptimalLocalDateTime();
        final String url = buildUrl(forecastRequestInfo, optimalLocalDateTime);
        ForecastResponseWrapper forecastResponseWrapper = restTemplateAdapter.requestAndGetBody(url, ForecastResponseWrapper.class);
        return StreamUtils.stream(forecastResponseWrapper.getForecast())
                .filter(this::isTodayForecastAndAfterNowTime)
                .collect(Collectors.toList());
    }

    private LocalDateTime findOptimalLocalDateTime() {
        return ForecastRequestTime.findOptimalForecastLocalDateTime(LocalDate.now(), LocalTime.now());
    }

    private String buildUrl(ForecastRequestInfo forecastRequestInfo, LocalDateTime optimalLocalDateTime) {
        final ForecastRequestParameter forecastRequestParameter = buildParameter(forecastRequestInfo, optimalLocalDateTime);
        return UrlUtils.buildUrl(forecastProperty.getBaseUrl(), forecastRequestParameter, false);
    }

    private boolean isTodayForecastAndAfterNowTime(ForecastResponse forecastResponse) {
        final String forecastDate = forecastResponse.getFcstDate();
        final String forecastTime = forecastResponse.getFcstTime();
        return isToday(forecastDate) && isAfterNow(forecastTime);
    }

    private boolean isToday(String forecastDate) {
        return !StringUtils.isEmpty(forecastDate) && LocalDate.parse(forecastDate, DATE_FORMATTER).compareTo(LocalDate.now()) == 0;
    }

    private boolean isAfterNow(String forecastTime) {
        return !StringUtils.isEmpty(forecastTime) && LocalTime.parse(forecastTime, TIME_FORMATTER).compareTo(LocalTime.now()) >= 1;
    }

    public static void main(String[] args) {
        final LocalTime then = LocalTime.of(10, 10);
        final LocalTime then2 = LocalTime.of(14, 10);
        final LocalTime now = LocalTime.now();
        System.out.println(then.compareTo(now));
        System.out.println(then2.compareTo(now));
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

    private String parse(LocalDate date) {
        return StringUtils.replace(date.toString(), "-", "");
    }

    private String parse(LocalTime time) {
        return StringUtils.replace(time.toString(), ":", "");
    }
}