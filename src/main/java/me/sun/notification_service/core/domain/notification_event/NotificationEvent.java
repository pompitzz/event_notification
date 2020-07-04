package me.sun.notification_service.core.domain.notification_event;

import lombok.*;
import me.sun.notification_service.core.domain.member.Member;
import me.sun.notification_service.web.notification.NotificationType;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationEvent {

    @Id
    @Column(name = "notification_event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationEventId;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private LocalTime notificationTime;

    @Column(nullable = false)
    private Long eventTargetTableId;

    @Embedded
    private SendDays sendDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
