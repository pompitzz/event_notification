package me.sun.notification_service.web.notification.slack.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
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

    public static Attachment from(List<Field> fields, String color) {
        final Attachment attachment = new Attachment();
        attachment.fields = fields;
        attachment.color = color;
        return attachment;
    }

    public static Attachment from(String title, String color) {
        final Attachment attachment = new Attachment();
        attachment.title = title;
        attachment.color = color;
        return attachment;
    }
}
