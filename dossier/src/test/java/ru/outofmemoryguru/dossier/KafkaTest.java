package ru.outofmemoryguru.dossier;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.annotation.KafkaListener;
import ru.outofmemoryguru.commondata.kafka.mappings.ActionToTopicMap;
import ru.outofmemoryguru.dossier.service.EmailFromDealListener;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KafkaTest {

    @Test
    void countTopics() {
        Set<String> topicsFromAnnotation = new HashSet<>();

        for (Method method : EmailFromDealListener.class.getDeclaredMethods()) {
            KafkaListener annotation = method.getAnnotation(KafkaListener.class);
            if (annotation != null) {
                topicsFromAnnotation.addAll(Arrays.asList(annotation.topics()));
            }
        }

        Set<String> topicsFromConstants = new HashSet<>();
        for (var field : ActionToTopicMap.class.getDeclaredFields()) {
            if (Modifier.isPublic(field.getModifiers())
                    && Modifier.isStatic(field.getModifiers())
                    && Modifier.isFinal(field.getModifiers())
                    && field.getType().equals(String.class)) {
                try {
                    Object value = field.get(null);
                    if (value instanceof String s) {
                        topicsFromConstants.add(s);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Заявленные топики" + topicsFromConstants);
        System.out.println("Прописанные топики" + topicsFromAnnotation);
        assertEquals(topicsFromConstants, topicsFromAnnotation,
                "topics в аннотации KafkaListener не соответсвуют реальным topics");
    }
}
