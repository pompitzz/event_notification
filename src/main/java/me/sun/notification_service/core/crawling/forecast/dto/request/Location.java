package me.sun.notification_service.core.crawling.forecast.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;

@Getter
@RequiredArgsConstructor
public class Location {
    private final String description;
    private final String nx;
    private final String ny;

    public static Location build(ForecastLocation forecastLocation) {
        return new Location(forecastLocation.getFullAddress(), forecastLocation.getLocationX(), forecastLocation.getLocationY());
    }
}
