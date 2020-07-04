package me.sun.notification_service.web.notification.slack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import me.sun.notification_service.web.notification.slack.model.Attachment;

import java.util.List;

@Data
@Builder
public class SlackArguments {
    @JsonIgnore
    private String token;
    private List<Attachment> attachments;
    private String channel;
    private String text;
}
