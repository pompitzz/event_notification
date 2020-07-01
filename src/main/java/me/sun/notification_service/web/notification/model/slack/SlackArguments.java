package me.sun.notification_service.web.notification.model.slack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import me.sun.notification_service.web.notification.model.slack.dto.Attachment;

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
