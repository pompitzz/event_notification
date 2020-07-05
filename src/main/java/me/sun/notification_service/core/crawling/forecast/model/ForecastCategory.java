package me.sun.notification_service.core.crawling.forecast.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForecastCategory {
    POP("강수확률", "%"),
//    REH("습도", "%"),
//    TMN("아침 최저기온", "°C"),
//    TMX("최고기온", "°C"),
    T3H("기온", "°C"),;


    private final String description;
    private final String unit;

    public static boolean isContains(String category) {
        for (ForecastCategory code : ForecastCategory.values()) {
            if(code.name().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRain(ForecastCategory category) {
        return ForecastCategory.POP == category;
    }

    public static boolean isNotRain(ForecastCategory category) {
        return !isRain(category);
    }
}

