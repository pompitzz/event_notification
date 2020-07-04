package me.sun.notification_service.core.domain.forecast.forecast_location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForecastLocationRepository extends JpaRepository<ForecastLocation,Long> {
    List<ForecastLocation> findByLocationXAndLocationY(String nx, String ny);
}
