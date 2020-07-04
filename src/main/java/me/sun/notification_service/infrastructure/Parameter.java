package me.sun.notification_service.infrastructure;

import lombok.Builder;
import lombok.Getter;
import me.sun.notification_service.infrastructure.utils.UrlUtils;

@Getter
@Builder
public class Parameter {
    private String key;
    private String value;

    public String encoding() {
        return UrlUtils.encode(key) + "=" + UrlUtils.encode(value);
    }

    public String nonEncoding() {
        return key + "=" + value;
    }
}
