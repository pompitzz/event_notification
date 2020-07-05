package me.sun.notification_service.core.domain.notification_event.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.member.QMember;
import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import me.sun.notification_service.core.domain.notification_event.QNotificationEvent;

import java.time.LocalTime;
import java.util.List;

import static me.sun.notification_service.core.domain.member.QMember.member;
import static me.sun.notification_service.core.domain.notification_event.QNotificationEvent.*;

@RequiredArgsConstructor
public class NotificationEventRepositoryImpl implements NotificationEventRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NotificationEvent> findByEventTime(LocalTime notificationTime) {
        return jpaQueryFactory
                .selectFrom(notificationEvent)
                .join(notificationEvent.member, member)
                .where(notificationEvent.notificationTime.eq(notificationTime))
                .fetch();
    }
}
