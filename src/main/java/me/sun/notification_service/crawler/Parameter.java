package me.sun.notification_service.crawler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Parameter {
    private String key;
    private String value;
}
