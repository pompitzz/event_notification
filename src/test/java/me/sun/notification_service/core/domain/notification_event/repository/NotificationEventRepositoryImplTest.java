package me.sun.notification_service.core.domain.notification_event.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.sun.notification_service.core.domain.member.Member;
import me.sun.notification_service.core.domain.member.MemberRepository;
import me.sun.notification_service.core.domain.notification_event.EventType;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.SendDays;
import me.sun.notification_service.web.notification.NotificationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationEventRepositoryImplTest {
    @Autowired
    NotificationEventRepository notificationEventRepository;
    @Autowired
    MemberRepository memberRepository;

    Member member;

    @BeforeEach
    void init() {
        member = memberRepository.save(Member.builder()
                .username("Dexter")
                .password("password")
                .slackTokenKey("token")
                .build());
    }

    @Test
    void findByNotificationTime() throws Exception {
        // given
        final LocalTime targetTime = LocalTime.of(8, 0);
        notificationEventRepository.save(notificationEvent(targetTime));
        notificationEventRepository.save(notificationEvent(LocalTime.of(8, 1)));
        notificationEventRepository.save(notificationEvent(LocalTime.of(8, 2)));
        notificationEventRepository.save(notificationEvent(LocalTime.of(9, 0)));

        // when
        final List<NotificationEvent> notificationEvents = notificationEventRepository.findByEventTime(targetTime);

        // then
        assertThat(notificationEvents.size()).isEqualTo(1);
        assertThat(notificationEvents.get(0).getNotificationTime()).isEqualTo(targetTime);
    }

    private NotificationEvent notificationEvent(LocalTime time) {
        return NotificationEvent.builder()
                .eventType(EventType.FORECAST)
                .notificationType(NotificationType.SLACK)
                .notificationTime(time)
                .eventTargetTableId(419L)
                .sendDays(SendDays.sendAllday())
                .member(member)
                .build();
    }
}