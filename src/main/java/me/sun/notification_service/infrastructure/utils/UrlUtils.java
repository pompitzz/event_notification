package me.sun.notification_service.infrastructure.utils;

import me.sun.notification_service.infrastructure.Parameter;
import me.sun.notification_service.infrastructure.ParameterBuilder;
import me.sun.notification_service.infrastructure.Parameterizable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.Param;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
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
        if (needParametersEncoding) {
            return buildUrlWithEncode(url, parameters);
        }
        return buildUrl(url, parameters);
    }

    public static String buildUrlWithEncode(String url, List<Parameter> parameters) {
        return buildUrl(url, buildQuery(parameters, Parameter::encoding));
    }

    public static String buildUrl(String url, List<Parameter> parameters) {
        return buildUrl(url, buildQuery(parameters, Parameter::nonEncoding));
    }

    private static String buildQuery(List<Parameter> parameters, Function<Parameter, String> f) {
        return StreamUtils.stream(parameters)
                .map(f).collect(Collectors.joining("&"));
    }

    private static String buildUrl(String url, String query) {
        if (StringUtils.isEmpty(query)) {
            return url;
        }
        return url + "?" + query;
    }
}
