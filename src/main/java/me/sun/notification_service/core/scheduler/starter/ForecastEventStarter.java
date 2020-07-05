package me.sun.notification_service.core.scheduler.starter;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.crawling.forecast.dto.request.ForecastRequestInfo;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocationQueryService;
import me.sun.notification_service.core.domain.member.Member;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.processor.forecast.ForecastProcessor;
import me.sun.notification_service.web.notification.NotificationInformation;
import me.sun.notification_service.web.notification.NotificationType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastEventStarter {

    private final ForecastLocationQueryService forecastLocationQueryService;
    private final ForecastProcessor forecastProcessor;

    public void start(NotificationEvent notificationEvent) {
        final ForecastLocation forecastLocation = forecastLocationQueryService.findByForecastLocationId(notificationEvent.getEventTargetTableId());
        final ForecastRequestInfo forecastRequestInfo = buildForecastRequestInfo(forecastLocation, notificationEvent.getNotificationType());
        final NotificationInformation notificationInformation = buildNotificationInformation(notificationEvent);
        forecastProcessor.process(forecastRequestInfo, forecastLocation, notificationInformation);
    }

    private NotificationInformation buildNotificationInformation(NotificationEvent notificationEvent) {
        final Member member = notificationEvent.getMember();
        return NotificationInformation.builder()
                .slackToken(member.getSlackTokenKey())
                .build();
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
