package me.sun.notification_service.web.notification.model.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sun.notification_service.web.notification.NotificationMessage;
import me.sun.notification_service.web.notification.NotificationService;
import me.sun.notification_service.web.notification.model.slack.dto.Attachment;
import me.sun.notification_service.web.notification.model.slack.dto.SlackNotificationPayload;
import me.sun.notification_service.web.notification.NotificationInformation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackNotificationService implements NotificationService {

    private final static String URL = "https://slack.com/api/chat.postMessage";

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public String sendMessage(SlackArguments slackArguments) {
        final String requestJson = formatToJson(slackArguments);
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, createSlackApiHeader(slackArguments.getToken()));
        log.info("Message를 전송합니다. payload: {}", requestJson);
        return restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class).getBody();
    }

    private HttpHeaders createSlackApiHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return headers;
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
//        sendMessage(notificationInformation.destination(), notificationMessage.toSlackMessage());
    }
}
