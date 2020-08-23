package com.victolee.fakemsa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victolee.fakemsa.dto.MailDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "email";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(MailDto mailDto) {
        String mailDataJson = this.convertDtoToJson(mailDto);
        this.kafkaTemplate.send(TOPIC, mailDataJson);
    }

    private String convertDtoToJson(MailDto mailDto) {
        ObjectMapper objectMapper = new ObjectMapper();

        String mailDataJson = null;
        try {
            mailDataJson = objectMapper.writeValueAsString(mailDto);
        } catch (JsonProcessingException e) {
            log.warn("json parsing error", e);
        }

        return mailDataJson;
    }
}

