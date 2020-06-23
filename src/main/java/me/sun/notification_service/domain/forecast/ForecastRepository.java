package me.sun.notification_service.domain.forecast;

import me.sun.notification_service.domain.town.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
    List<Forecast> findByForecastDateAndTown(LocalDate forecastDate, Town town);
}
