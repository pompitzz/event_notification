package me.sun.notification_service.core.scheduler.starter;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestInfo;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocationQueryService;
import me.sun.notification_service.core.processor.forecast.ForecastProcessor;
import me.sun.notification_service.web.notification.NotificationType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastEventStarter {

    private final ForecastLocationQueryService forecastLocationQueryService;
    private final ForecastProcessor forecastProcessor;

    public void start(Long targetTableId, NotificationType notificationType) {
        final ForecastLocation forecastLocation = forecastLocationQueryService.findByForecastLocationId(targetTableId);
        forecastProcessor.process(buildForecastRequestInfo(forecastLocation, notificationType), forecastLocation);
    }

    private ForecastRequestInfo buildForecastRequestInfo(ForecastLocation forecastLocation, NotificationType notificationType) {
        return ForecastRequestInfo.builder()
                .notificationType(notificationType)
                .locationDescription(forecastLocation.getAddressDetail())
                .locationNx(forecastLocation.getLocationX())
                .locationNy(forecastLocation.getLocationY())
                .build();
    }
}
