package me.sun.notification_service.core.domain.notification_event;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationEvent {

    @Id
    @Column(name = "notification_event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationEventId;
}
