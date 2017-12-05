package com.leon.study.common;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class clazz, Object... entries) {
        super(ResourceNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, entries)));
    }

    private static String generateMessage(String resource, Map<String, String> searchParams) {
        return StringUtils.capitalize(resource) + " was not found for parameters " + searchParams;
    }

    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1) throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2)
                .map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
