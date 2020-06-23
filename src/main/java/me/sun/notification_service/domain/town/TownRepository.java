package me.sun.notification_service.domain.town;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TownRepository extends JpaRepository<Town ,Long> {
    List<Town> findByLocationXAndLocationY(String nx, String ny);
}
