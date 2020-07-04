package me.sun.notification_service.core.domain.forecast.forecast_location;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ForecastLocationQueryService {

    private final ForecastLocationRepository forecastLocationRepository;

    public ForecastLocation findByForecastLocationId(Long id) {
        final Optional<ForecastLocation> forecastLocation = forecastLocationRepository.findById(id);
        // TODO 에러 발생시 어떻게 할껀지
        return forecastLocation.orElseThrow(() -> new IllegalArgumentException("잘못된 정보"));
    }
}
