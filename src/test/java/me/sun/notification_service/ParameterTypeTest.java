package me.sun.notification_service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterTypeTest {

    static class Sub<T> {
        T value;
    }

    @Test
    @DisplayName("이레이저 동작 확인")
    void parameter() throws Exception {
        Sub<String> s = new Sub<>();
        assertThat(s.getClass()).isEqualTo(Sub.class);

        final Field valueField = s.getClass().getDeclaredField("value");
        assertThat(valueField.getType())
                .as("이레이저로 인해 런타임시에는 실제론 Object이다.")
                .isEqualTo(Object.class);
    }

    static class SubString extends Sub<String> {
    }

    @Test
    @DisplayName("수퍼 타입 토큰의 기능 확인")
    void superType() throws Exception {
        final SubString subString = new SubString();
        final Class<? extends SubString> aClass = subString.getClass();
        assertThat(aClass).isEqualTo(SubString.class);

        final Type type = aClass.getGenericSuperclass();
        final ParameterizedType parameterizedType = (ParameterizedType) type;
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        assertThat(actualTypeArguments.length).isEqualTo(1);
        System.out.println(actualTypeArguments[0]);
    }

    @Test
    @DisplayName("이너 클래스로도 가능하다.")
    void superType2() throws Exception {
        final Sub sub = new Sub<List<String>>() {
        };
        final ParameterizedType parameterizedType = (ParameterizedType) sub.getClass().getGenericSuperclass();
        System.out.println(parameterizedType.getRawType());
        final ParameterizedType parameterizedType1 = makeParameterizedType(String.class);
        System.out.println(parameterizedType1.getRawType());
    }

    @Test
    void test3() throws Exception {
//        final ParameterizedType print = print(String.class);
        new ParameterizedTypeReference<List<String>>(){};
    }

    <T> ParameterizedType makeParameterizedType(Class<T> aClass) {
        Type type = (Class<?>) aClass;
        final ParameterizedType parameterizedType = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
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
        return parameterizedType;
    }


}
