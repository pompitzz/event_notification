package me.sun.notification_service.utils;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public class StreamUtils {
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (Objects.isNull(collection)) {
            return Stream.empty();
        }
        return collection.stream();
    }
}
