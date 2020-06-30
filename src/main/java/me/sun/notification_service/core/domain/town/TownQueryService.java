package me.sun.notification_service.core.domain.town;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.response.ForecastResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TownQueryService {

    private final TownRepository townRepository;

    public Town findTown(ForecastResponse forecastResponse) {
        final String nx = forecastResponse.getNx();
        final String ny = forecastResponse.getNy();
        final List<Town> result = townRepository.findByLocationXAndLocationY(nx, ny);
        return Optional.ofNullable(result.get(0))
                .orElseThrow(() -> new IllegalArgumentException(String.format("nx: %s, ny: %s", nx, ny)));
    }
}
