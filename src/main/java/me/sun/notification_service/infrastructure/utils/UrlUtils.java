package me.sun.notification_service.infrastructure.utils;

import me.sun.notification_service.infrastructure.Parameter;
import me.sun.notification_service.infrastructure.ParameterBuilder;
import me.sun.notification_service.infrastructure.Parameterizable;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class UrlUtils {
    public static String decode(String url) {
        return URLDecoder.decode(url, Charset.defaultCharset());
    }

    public static String encode(String url) {
//        return URLEncoder.encode(url, StandardCharsets.UTF_8);
        return url;
    }

    public static <T extends Parameterizable> String buildUrl(String url, T target) {
        List<Parameter> parameters = ParameterBuilder.build(target);
        return buildUrl(url, parameters);
    }

    public static String buildUrl(String url, List<Parameter> parameters) {
        String params = StreamUtils.stream(parameters)
                .map(UrlUtils::query)
                .collect(Collectors.joining("&"));
        return url + "?" + params;
    }

    static String query(Parameter parameter) {
        return String.format("%s=%s", encode(parameter.getKey()), encode(parameter.getValue()));
    }

    //http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&base_date=20200618&base_time=0500&nx=1&ny=1&
    //http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%2BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%2BcYFsl1K3H53SuqAaXLC8sg%3D%3D&pageNo=1&numOfRows=30&dataType=JSON&base_date=20200618&base_time=0200&nx=1&ny=1
    //http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=Iyyt0N7%25252BZJXHYNGM2uvMOpqW5N123G99dY9wVBWlXiGXUZxGpu8S86lj3RqLvOK%25252BcYFsl1K3H53SuqAaXLC8sg%25253D%25253D&pageNo=1&numOfRows=30&dataType=JSON&base_date=20200618&base_time=0200&nx=1&ny=1
}
