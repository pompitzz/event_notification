package me.sun.notification_service.core.crawling.forecast;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequest;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastTime;
import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.response.ResponseDto;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
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

    public List<ForecastResponse> request(LocalTime time, Location location, int size) {
        final ForecastRequest forecastRequest = ForecastRequest.builder()
                .serviceKey(forecastProperty.getServiceKey())
                .base_date(findDate())
                .base_time(findTime(time))
                .nx(location.getNx())
                .ny(location.getNy())
                .numOfRows(size)
                .pageNo(1)
                .dataType("JSON")
                .build();

        String url = UrlUtils.buildUrl(forecastProperty.getBaseUrl(), forecastRequest, false);

        ResponseDto responseDto = restTemplateAdapter.requestAndGetBody(url, ResponseDto.class);
        return Objects.requireNonNull(responseDto).getForecast();
    }

    private static String findDate() {
        LocalDate date = LocalDate.now().minusDays(1);
        return StringUtils.replace(date.toString(), "-", "");
    }

    private String findTime(LocalTime time) {
        return ForecastTime.findOptimalForecastTime(time).timeString();
    }
}