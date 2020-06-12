package me.sun.notification_service.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

    private static final ZoneOffset KST = ZoneOffset.ofHours(9);

    public static Date parse(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(KST));
    }

    public static Date parse(int year, int month, int day, int minute, int second) {
        return parse(LocalDateTime.of(year, month, day, minute, second));
    }
}
