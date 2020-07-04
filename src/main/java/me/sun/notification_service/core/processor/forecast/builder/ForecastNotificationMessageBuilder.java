package me.sun.notification_service.core.processor.forecast.builder;

import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.processor.forecast.model.ForecastResult;
import me.sun.notification_service.web.notification.NotificationMessageBuilder;

public interface ForecastNotificationMessageBuilder<R> extends NotificationMessageBuilder<R, ForecastResult, ForecastLocation> {
}
