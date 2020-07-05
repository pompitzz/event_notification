package me.sun.notification_service.core.domain.forecast.forecast_location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ForecastLocationRepository extends JpaRepository<ForecastLocation,Long> {
    List<ForecastLocation> findByLocationXAndLocationY(String nx, String ny);
}
