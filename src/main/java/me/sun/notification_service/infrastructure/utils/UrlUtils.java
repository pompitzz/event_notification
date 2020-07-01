package me.sun.notification_service.infrastructure.utils;

import me.sun.notification_service.infrastructure.Parameter;
import me.sun.notification_service.infrastructure.ParameterBuilder;
import me.sun.notification_service.infrastructure.Parameterizable;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UrlUtils {
    public static String decode(String url) {
        return URLDecoder.decode(url, Charset.defaultCharset());
    }

    public static String encode(String url) {
        return URLEncoder.encode(url, StandardCharsets.UTF_8);
    }

    public static <T extends Parameterizable> String buildUrl(String url, T target, boolean needParametersEncoding) {
        List<Parameter> parameters = ParameterBuilder.build(target);
        return needParametersEncoding ? buildUrlWithEncode(url, parameters) : buildUrl(url, parameters);
    }

    public static String buildUrlWithEncode(String url, List<Parameter> parameters) {
        final String query = StreamUtils.stream(parameters)
                .map(param -> String.format("%s=%s", encode(param.getKey()), encode(param.getValue())))
                .collect(Collectors.joining("&"));
        return buildUrl(url, query);
    }

    public static String buildUrl(String url, List<Parameter> parameters) {
        final String query = StreamUtils.stream(parameters)
                .map(param -> String.format("%s=%s", param.getKey(), param.getValue()))
                .collect(Collectors.joining("&"));
        return buildUrl(url, query);
    }

    private static String buildUrl(String url, String query) {
        if (StringUtils.isEmpty(query)) {
            return url;
        }
        return url + "?" + query;
    }

}
