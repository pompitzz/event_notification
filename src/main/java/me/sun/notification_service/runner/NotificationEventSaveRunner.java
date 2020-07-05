package me.sun.notification_service.runner;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.member.MemberRepository;
import me.sun.notification_service.core.domain.notification_event.EventType;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.repository.NotificationEventRepository;
import me.sun.notification_service.core.domain.notification_event.SendDays;
import me.sun.notification_service.web.notification.NotificationType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;


@Component
@Profile("dev")
@RequiredArgsConstructor
public class NotificationEventSaveRunner implements ApplicationRunner {

    private final NotificationEventRepository notificationEventRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        notificationEventRepository.saveAll(
                List.of(build(16), build(17), build(18))
        );
    }
    private NotificationEvent build(int hour) {
        return NotificationEvent.builder()
                .eventType(EventType.FORECAST)
                .notificationType(NotificationType.SLACK)
                .notificationTime(LocalTime.of(hour, 0))
                .eventTargetTableId(419L)
                .sendDays(SendDays.sendAllday())
                .member(memberRepository.findById(1L).get())
                .build();
    }
}
