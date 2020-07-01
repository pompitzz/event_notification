package me.sun.notification_service.core.crawling.forecast.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.sun.notification_service.core.domain.forecast.town.Town;

@Getter
@RequiredArgsConstructor
public class Location {
    private final String description;
    private final String nx;
    private final String ny;

    public static Location build(Town town) {
        return new Location(town.getFullAddress(), town.getLocationX(), town.getLocationY());
    }
}
