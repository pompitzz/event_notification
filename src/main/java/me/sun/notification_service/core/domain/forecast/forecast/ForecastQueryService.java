package me.sun.notification_service.core.domain.forecast.forecast;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.core.crawling.forecast.model.ForecastCategory;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocationQueryService;
import me.sun.notification_service.infrastructure.utils.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastQueryService {

    private final ForecastRepository forecastRepository;
    private final ForecastLocationQueryService forecastLocationQueryService;

    public List<Forecast> saveForecasts(List<ForecastResponse> forecastResponses, ForecastLocation forecastLocation) {
        if (CollectionUtils.isEmpty(forecastResponses)) {
            return Collections.emptyList();
        }

        final List<Forecast> forecasts = StreamUtils.stream(forecastResponses)
                .filter(this::isContainsInForecastCategory)
                .map(response -> response.toEntity(forecastLocation))
                .collect(Collectors.toList());

        final List<Forecast> result = forecastRepository.saveAll(forecasts);
        log.info("### [Saved Forecasts] size: {}, town: {}", forecasts.size(), forecastLocation.getFullAddress());
        return result;
    }

    private boolean isContainsInForecastCategory(ForecastResponse forecastResponse) {
        return ForecastCategory.isContains(forecastResponse.getCategory());
    }

    public List<Forecast> findYesterdayForecast(Forecast forecast) {
        final ForecastLocation forecastLocation = forecast.getForecastLocation();
        final LocalDate date = forecast.getForecastDate().minusDays(1);
        return Optional.ofNullable(forecastRepository.findByForecastDateAndForecastLocation(date, forecastLocation))
                .orElse(Collections.emptyList());
    }
}
