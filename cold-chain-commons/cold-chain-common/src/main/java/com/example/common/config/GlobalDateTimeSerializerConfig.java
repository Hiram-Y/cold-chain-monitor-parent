package com.example.common.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Configuration
public class GlobalDateTimeSerializerConfig {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(PATTERN));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(PATTERN));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // return new Jackson2ObjectMapperBuilderCustomizer() {
        //     @Override
        //     public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        //         jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //         jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
        //         jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
        //     }
        // };

        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
            builder.simpleDateFormat(PATTERN);
        };
    }
}
