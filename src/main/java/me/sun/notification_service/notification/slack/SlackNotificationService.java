package me.sun.notification_service.notification.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.sun.notification_service.crawler.NotificationMessage;
import me.sun.notification_service.notification.NotificationService;
import me.sun.notification_service.notification.slack.dto.Attachment;
import me.sun.notification_service.notification.slack.dto.SlackNotificationPayload;
import me.sun.notification_service.schedule.NotificationInformation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlackNotificationService implements NotificationService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public String sendMessage(String url, SlackNotificationPayload payload) {
        final String requestJson = formatToJson(payload);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
    }

    public String sendMessage(String url, Attachment attachment) {
        return sendMessage(url, new SlackNotificationPayload(Collections.singletonList(attachment)));
    }

    public String sendMessage(String url, List<Attachment> attachments) {
        return sendMessage(url, new SlackNotificationPayload(attachments));
    }

    private <T> String formatToJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Json 데이터로 파싱할 수 없습니다.", e);
        }
    }

    @Override
    public void sendMessage(NotificationInformation notificationInformation, NotificationMessage notificationMessage) {
        sendMessage(notificationInformation.destination(), notificationMessage.toSlackMessage());
    }
}
