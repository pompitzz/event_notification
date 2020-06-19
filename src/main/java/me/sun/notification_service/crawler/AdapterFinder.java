package me.sun.notification_service.crawler;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.domain.notification_event.EventType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdapterFinder {
    private final List<Adapter> adapters;

    public Adapter find(EventType type) {
        for (Adapter adapter : adapters) {
            if (adapter.isType(type))
                return adapter;
        }
        throw new IllegalArgumentException("");
    }
}
