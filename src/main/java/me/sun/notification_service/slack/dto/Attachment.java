package me.sun.notification_service.slack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class Attachment {
    private String color;
    private String pretext;
    private String authorName;
    private String authorLink;
    private String author_icon;
    private String title;
    private String title_link;
    private String text;
    private List<Field> fields;
    private String imageUrl;
    private String thumbUrl;
    private String footer;
    private String footer_icon;
    private String ts;
}
