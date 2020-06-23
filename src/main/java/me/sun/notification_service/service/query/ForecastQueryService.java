package me.sun.notification_service.service.query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.crawler.forecast.dto.response.ForecastResponse;
import me.sun.notification_service.crawler.forecast.model.ForecastCategory;
import me.sun.notification_service.domain.forecast.Forecast;
import me.sun.notification_service.domain.forecast.ForecastRepository;
import me.sun.notification_service.domain.town.Town;
import me.sun.notification_service.service.query.TownQueryService;
import me.sun.notification_service.utils.StreamUtils;
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
    private final TownQueryService townQueryService;

    public List<Forecast> saveForecasts(List<ForecastResponse> forecastResponses) {
        if (CollectionUtils.isEmpty(forecastResponses)) {
            return Collections.emptyList();
        }

        final Town town = townQueryService.findTown(forecastResponses.get(0));

        final List<Forecast> forecasts = StreamUtils.stream(forecastResponses)
                .filter(this::isOnlyForecastCategory)
                .map(response -> response.toEntity(town))
                .collect(Collectors.toList());

        final List<Forecast> result = forecastRepository.saveAll(forecasts);
        log.info("### [Saved Forecasts] size: {}, town: {}", forecasts.size(), town.getFullAddress());
        return result;
    }

    private boolean isOnlyForecastCategory(ForecastResponse forecastResponse) {
        return ForecastCategory.isContains(forecastResponse.getCategory());
    }

    public List<Forecast> findYesterdayForecast(Forecast forecast) {
        final Town town = forecast.getTown();
        final LocalDate date = forecast.getForecastDate().minusDays(1);
        return Optional.ofNullable(forecastRepository.findByForecastDateAndTown(date, town))
                .orElse(Collections.emptyList());
    }
}
