package me.sun.notification_service.infrastructure;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ParameterBuilder {

    private static final Set<Class<?>> parameterizableTypes;

    static {
        List<Class<?>> classes = Arrays.asList(Integer.class, int.class, String.class, Long.class, long.class);
        parameterizableTypes = Set.copyOf(classes);
    }

    public static <T extends Parameterizable> List<Parameter> build(T target) {
        return build(target, new ArrayList<>());
    }

    private static <T extends Parameterizable> List<Parameter> build(T target, List<Parameter> parameters) {
        Class<? extends Parameterizable> targetClass = target.getClass();
        for (Field field : targetClass.getDeclaredFields()) {
            addParameter(target, field, parameters);
        }
        return parameters;
    }

    private static <T extends Parameterizable> void addParameter(T target, Field field, List<Parameter> parameters) {
        if (parameterizableTypes.contains(field.getType())) {
            try {
                field.setAccessible(true);
                String value = field.get(target).toString();
                Parameter parameter = Parameter.builder()
                        .key(field.getName())
                        .value(value)
                        .build();
                parameters.add(parameter);
            } catch (IllegalAccessException | NullPointerException e) {
                log.error("필드 값을 가져오지 못했습니다. target: {}, field: {}, error: {}", target, field.getName(), e);
            }
        }
    }
}
