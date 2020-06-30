package me.sun.notification_service.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class StreamUtilsTest {
    @Test
    void return_empty()throws Exception {
        List<String> list = null;
        assertThat(StreamUtils.stream(list)).isEmpty();
    }
}