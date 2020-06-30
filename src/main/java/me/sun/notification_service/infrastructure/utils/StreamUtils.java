package me.sun.notification_service.infrastructure.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (Objects.isNull(collection)) {
            return Stream.empty();
        }
        return collection.stream();
    }

    public static <T> List<T> toList(Collection<T> collection, Predicate<T> predicate) {
        return stream(collection).filter(predicate).collect(Collectors.toList());
    }

}
