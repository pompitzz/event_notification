package me.sun.notification_service.crawler;

import lombok.RequiredArgsConstructor;
import me.sun.notification_service.domain.EventType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlerFinderService {
    private final List<Crawler> crawlers;

    public Crawler find(EventType type) {
        for (Crawler crawler : crawlers) {
            if (crawler.isType(type))
                return crawler;
        }
        throw new IllegalArgumentException("");
    }

    public NotificationMessage crawling(EventType type) {
        return find(type).crawling();
    }
}
