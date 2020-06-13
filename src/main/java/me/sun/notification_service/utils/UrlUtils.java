package me.sun.notification_service.utils;

import me.sun.notification_service.ParameterType;

import java.net.URLDecoder;
import java.nio.charset.Charset;

public class UrlUtils {
    public static String decode(String path) {
        return URLDecoder.decode(path, Charset.defaultCharset());
    }
}
