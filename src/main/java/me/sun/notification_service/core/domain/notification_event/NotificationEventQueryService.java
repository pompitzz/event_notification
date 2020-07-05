package me.sun.notification_service.core.domain.notification_event;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.notification_event.repository.NotificationEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationEventQueryService {

    private final NotificationEventRepository notificationEventRepository;

    public List<NotificationEvent> getNotificationEvents() {
        return notificationEventRepository.findAllWithMember();
    }

    public List<NotificationEvent> getEventsByNotificationTime(LocalTime notificationTime) {
        return notificationEventRepository.findByEventTime(notificationTime);
    }

}
