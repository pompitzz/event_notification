package me.sun.notification_service.notification

import com.fasterxml.jackson.databind.ObjectMapper
import me.sun.notification_service.notification.slack.SlackNotificationService
import me.sun.notification_service.slack.dto.Attachment
import me.sun.notification_service.slack.dto.SlackNotificationPayload
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SlackNotificationServiceTest extends Specification {
    private ObjectMapper objectMapper = Mock()
    private RestTemplate restTemplate = Mock()
    
    @Subject
    private SlackNotificationService subject
    private def url = "someUrl"
    
    def setup(){
        subject = new SlackNotificationService(objectMapper, restTemplate)
    }

    @Unroll
    def "#target 파라미터로 메시지 전송이 성공해야 한다."(){
        when:
        def result = subject.sendMessage(url, Mock(target))

        then:
        1 * objectMapper.writeValueAsString(_) >> "json"
        1 * restTemplate.postForObject(url, "json", String.class) >> response
        result == response

        where:
        target                         | response
        SlackNotificationPayload.class | 'SlackNotificationPayload'
        List.class                     | 'List'
        Attachment.class               | 'Attachment'
    }
}
