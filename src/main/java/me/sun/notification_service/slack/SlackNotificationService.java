package me.sun.notification_service.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.sun.notification_service.slack.dto.Attachment;
import me.sun.notification_service.slack.dto.SlackNotificationPayload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SlackNotificationService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public String sendMessage(String url, SlackNotificationPayload payload){
        final String requestJson = formatToJson(payload);
        return restTemplate.postForObject(url, requestJson, String.class);
    }

    public String sendMessage(String url, Attachment attachment){
        return sendMessage(url, new SlackNotificationPayload(Collections.singletonList(attachment)));
    }

    public String sendMessage(String url, List<Attachment> attachments){
        return sendMessage(url, new SlackNotificationPayload(attachments));
    }

    private <T> String formatToJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Json 데이터로 파싱할 수 없습니다.", e);
        }
    }
}
