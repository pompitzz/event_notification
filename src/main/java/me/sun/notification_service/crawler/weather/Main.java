package me.sun.notification_service.crawler.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import me.sun.notification_service.crawler.HtmlParser;
import me.sun.notification_service.utils.UrlUtils;
import org.jsoup.nodes.Document;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    private static RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
//        final String path = "https://www.weather.go.kr/w/wnuri-fct/weather/today-vshortmid.do?code=1174062000&unit=km%2Fh";
//        final Document document = HtmlParser.get(path);
//        cityInfoMaker();
        System.out.println(List.class.getTypeParameters().length);
        System.out.println(String.class.getTypeParameters().length);
        System.out.println(Map.class.getTypeParameters().length);


//        System.out.println(document);
    }

    public static void cityInfoMaker() {
//        final String city = "https://www.weather.go.kr/w/rest/zone/dong.do?type=CITY&wideCode=1100000000&cityCode=&keyword=&keywordStart=&keywordEnd=";
//        final ParameterizedTypeReference<List<City>> responseType = new ParameterizedTypeReference<>() {};
//        final List<City> body = restTemplate.exchange(city, HttpMethod.GET, null, responseType).getBody();
////        System.out.println(body);
        System.out.println(List.class.getGenericSuperclass());
    }

    @AllArgsConstructor
    static class CustomParameterizedType implements ParameterizedType{

        private Class<?> rawType;
        private Type type;

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return rawType;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
    public static <T> List<T> getList(String url, Class<T> clazz) {
        final ParameterizedType parameterizedType = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{(Type) clazz};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<T>>() {
            @Override
            public Type getType() {
                return parameterizedType;
            }
        }).getBody();
    }

    public static <T> List<City> getList2(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<City>>() {
        }).getBody();
    }

    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class City {
        private String code;
        private String name;
        private String shortName;
    }
}
