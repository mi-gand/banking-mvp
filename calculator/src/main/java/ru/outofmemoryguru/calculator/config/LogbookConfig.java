package ru.outofmemoryguru.calculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@Configuration
public class LogbookConfig {

    @Bean
    public Logbook logbook(ObjectMapper objectMapper) {
        ObjectMapper logbookMapper = objectMapper.copy().enable(SerializationFeature.INDENT_OUTPUT);

        JsonHttpLogFormatter formatter = new JsonHttpLogFormatter(logbookMapper);
        return Logbook.builder()
                .sink(new DefaultSink(
                        formatter,
                        new DefaultHttpLogWriter()
                ))
                .build();
    }
}
