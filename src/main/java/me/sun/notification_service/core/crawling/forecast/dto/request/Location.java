package me.sun.notification_service.core.crawling.forecast.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public @Getter
@RequiredArgsConstructor
enum Location {
    A("a", 1, 1);
    private final String description;
    private final Integer nx;
    private final Integer ny;
}