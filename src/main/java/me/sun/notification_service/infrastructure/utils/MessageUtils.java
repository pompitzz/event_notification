package me.sun.notification_service.infrastructure.utils;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MessageUtils {
    public static <T> String joining(String delimiter, Collection<T> collection, Function<T, String> function) {
        return StreamUtils.stream(collection)
                .map(function)
                .collect(Collectors.joining(delimiter));
    }
}
