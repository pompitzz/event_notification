package me.sun.notification_service.infrastructure.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtils {
    public static Document get(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new IllegalArgumentException("Connection 실패! URL을 확인하세요. Url: " + url);
        }
    }
}
