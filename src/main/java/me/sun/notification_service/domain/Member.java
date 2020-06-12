package me.sun.notification_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.util.StringUtils;

@Builder
@AllArgsConstructor
public class Member {

    private String slackUrl;
    private NotificationType notificationType;

    public String url() {
        switch (notificationType) {
            case SLACK:
                return getSlackUrl();
            default:
                throw new IllegalArgumentException("");
        }
    }

    private String getSlackUrl() {
        if (StringUtils.isEmpty(slackUrl)) {
            throw new IllegalArgumentException("");
        }
        return slackUrl;
    }
}
