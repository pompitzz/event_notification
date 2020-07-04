package me.sun.notification_service.core.domain.notification_event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationEventQueryService {

    private final NotificationEventRepository notificationEventRepository;

    public List<NotificationEvent> getNotificationEvents() {
        return notificationEventRepository.findAll();
    }

}
