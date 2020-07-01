package me.sun.notification_service.infrastructure.wrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RestTemplateAdapter {

    private final RestTemplate restTemplate;

    public <T> T requestAndGetBody(String url, Class<T> clazz) {
        return restTemplate.exchange(url, HttpMethod.GET, null, clazz).getBody();
    }

    public <T> List<T> requestAndGetList(String url, HttpMethod method, Class<T> clazz) {
        return request(url, method, clazz).getBody();
    }

    public <T> List<T> requestAndGetList(String url, Class<T> clazz) {
        return request(url, HttpMethod.GET, clazz).getBody();
    }

    public <T> ResponseEntity<List<T>> request(String url, HttpMethod method, Class<T> clazz) {
        return restTemplate.exchange(url, method, null,
                new ParameterizedTypeReference<List<T>>() {
                    @Override
                    public Type getType() {
                        return new CustomParameterizedType(List.class, clazz);
                    }
                });
    }


    private static class CustomParameterizedType implements ParameterizedType {

        private Class<?> rawType;
        private Type type;

        public CustomParameterizedType(Class<?> rawType, Type type) {
            if (rawType.getTypeParameters().length == 0) {
                throw new IllegalArgumentException("rawType은 TypeParameter가 존재해야 합니다.");
            }
            this.rawType = rawType;
            this.type = type;
        }

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
}
