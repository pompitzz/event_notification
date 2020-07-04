package me.sun.notification_service.core.crawling.forecast.dto.request;

import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.web.notification.NotificationType;

import java.time.LocalTime;

@Getter
@Builder
public class ForecastRequestInfo {
    private NotificationType notificationType;
    private String locationDescription;
    private String locationNx;
    private String locationNy;

}
