package me.sun.notification_service.core.crawling.forecast.model;

import lombok.Getter;
import me.sun.notification_service.core.crawling.forecast.dto.request.Location;
import me.sun.notification_service.web.notification.model.common.NotificationType;

import java.time.LocalTime;

@Getter
public class ForecastRequestInfo {
    private NotificationType notificationType;
    private LocalTime notificationTime;
    private Location location;
    private int size;

    public String getNx() {
        return location.getNx();
    }

    public String getNy() {
        return location.getNy();
    }

    public String getDescription() {
        return location.getDescription();
    }
}
