package com.victolee.fakemsa.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
    /* 토픽이 없을 경우 생성할 수 있도록 bean 생성 */
    @Bean
    public NewTopic topic1() {
        return new NewTopic("email", 1, (short) 1);
    }

}
