package me.sun.notification_service.core.domain.notification_event.repository;

import me.sun.notification_service.core.domain.notification_event.NotificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationEventRepository extends JpaRepository<NotificationEvent, Long>, NotificationEventRepositoryCustom {

    @Query("select ne from NotificationEvent as ne join fetch ne.member as m")
    List<NotificationEvent> findAllWithMember();
}
