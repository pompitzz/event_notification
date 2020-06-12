package me.sun.notification_service.crawler.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Content {
    private String title;
    private String articleUrl;
    private String description;
    private boolean isAbsoluteUrl;

    @Override
    public String toString() {
        return "Content{" +
                "\ntitle='" + title +
                "\narticleUrl='" + articleUrl +
                "\ndescription='" + description +
                "\nisAbsoluteUrl=" + isAbsoluteUrl +
                '}';
    }
}
