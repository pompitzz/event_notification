package me.sun.notification_service.crawler.weather;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ForecastCode {
    POP("강수확률", "%"),
    REH("습도", "%"),
    TMN("아침 최저기온", "°C"),
    TMX("낮 최고기온", "°C"),
    T3H("3시간 기온", "°C"),;


    private final String description;
    private final String unit;
}

