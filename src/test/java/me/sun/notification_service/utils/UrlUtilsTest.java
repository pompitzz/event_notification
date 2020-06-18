package me.sun.notification_service.utils;

import me.sun.notification_service.crawler.Parameter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UrlUtilsTest {
    @Test
    void build_Url() throws Exception {
        String answer = "http://naver.com?name=Dexter&age=13&city=seoul";
        List<Parameter> parameters = Arrays.asList(
                Parameter.builder().key("name").value("Dexter").build(),
                Parameter.builder().key("age").value("13").build(),
                Parameter.builder().key("city").value("seoul").build()
        );

        String urlWithParams = UrlUtils.buildUrl("http://naver.com", parameters);
        assertEquals(urlWithParams, answer);
    }
}