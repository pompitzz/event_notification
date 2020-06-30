package me.sun.notification_service.core.crawling.forecast;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestParam;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastTime;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.dto.response.ResponseDto;
import me.sun.notification_service.core.crawling.forecast.model.ForecastProperty;
import me.sun.notification_service.infrastructure.utils.UrlUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ForecastAdapter {

    private final ForecastProperty forecastProperty;
    private final RestTemplate restTemplate;

    public List<ForecastResponse> request(LocalTime time, String nx, String ny, int size) {
        final String timeStr = ForecastTime.findOptimalForecastTime(time).timeString();
        var param = ForecastRequestParam.build(forecastProperty.getServiceKey(), timeStr, nx, ny, size);

        String url = UrlUtils.buildUrl(forecastProperty.getBaseUrl(), param);
        System.out.println(url);
        ResponseDto responseDto = restTemplate.getForObject(url, ResponseDto.class);
        return Objects.requireNonNull(responseDto).getForecast();
    }
}