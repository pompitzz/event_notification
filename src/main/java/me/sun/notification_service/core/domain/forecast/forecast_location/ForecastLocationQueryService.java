package me.sun.notification_service.core.domain.forecast.town;

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

    public ForecastLocation findTown(ForecastResponse forecastResponse) {
        final String nx = forecastResponse.getNx();
        final String ny = forecastResponse.getNy();
        final List<ForecastLocation> result = forecastLocationRepository.findByLocationXAndLocationY(nx, ny);
        return Optional.ofNullable(result.get(0))
                .orElseThrow(() -> new IllegalArgumentException(String.format("nx: %s, ny: %s", nx, ny)));
    }
}
