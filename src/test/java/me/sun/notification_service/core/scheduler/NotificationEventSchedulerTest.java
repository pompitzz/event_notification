package me.sun.notification_service.core.scheduler;

import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocation;
import me.sun.notification_service.core.domain.forecast.forecast_location.ForecastLocationRepository;
import me.sun.notification_service.core.domain.member.Member;
import me.sun.notification_service.core.domain.member.MemberRepository;
import me.sun.notification_service.core.domain.notification_event.EventType;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.SendDays;
import me.sun.notification_service.core.domain.notification_event.repository.NotificationEventRepository;
import me.sun.notification_service.web.notification.NotificationType;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@Disabled
@SpringBootTest
class NotificationEventSchedulerTest {

    @Autowired
    private NotificationEventScheduler notificationEventScheduler;
    @Autowired
    private NotificationEventRepository notificationEventRepository;
    @Autowired
    private ForecastLocationRepository forecastLocationRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Ignore
    @Test
    void startForecastEvent() throws Exception {
        // given & when
        final Member member = saveMember();
        final ForecastLocation savedForecastLocation = saveForecastLocation();
        saveNotificationEvent(member, savedForecastLocation);

        // then
        notificationEventScheduler.hourlySchedule();
    }

    private void saveNotificationEvent(Member member, ForecastLocation savedForecastLocation) {
        final NotificationEvent notificationEvent = NotificationEvent.builder()
                .eventTargetTableId(savedForecastLocation.getForecastLocationId())
                .eventType(EventType.FORECAST)
                .notificationTime(LocalTime.now())
                .notificationType(NotificationType.SLACK)
                .member(member)
                .sendDays(allDays())
                .build();

        notificationEventRepository.save(notificationEvent);
    }

    private ForecastLocation saveForecastLocation() {
        final ForecastLocation forecastLocation = ForecastLocation.builder()
                .state("서울특별시")
                .city("종로구")
                .addressDetail("이화동")
                .locationX("60")
                .locationY("127")
                .build();

        return forecastLocationRepository.save(forecastLocation);
    }

    private Member saveMember() {
        final Member member = Member.builder()
                .username("Dexter")
                .password("password")
                .slackTokenKey("token")
                .build();
        return memberRepository.save(member);
    }

    private SendDays allDays() {
        return SendDays.builder()
                .sendMonday(true)
                .sendTuesDay(true)
                .sendWednesday(true)
                .sendThursday(true)
                .sendFriday(true)
                .sendSaturday(true)
                .sendSunday(true)
                .build();
    }

}