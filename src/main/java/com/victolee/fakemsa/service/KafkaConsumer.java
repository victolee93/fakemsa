package com.victolee.fakemsa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victolee.fakemsa.dto.MailDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class KafkaConsumer {
    private final MailService mailService;

    @KafkaListener(topics = "email", groupId = "group_id")
    public void consume(String message) {
        MailDto mailDto = this.convertJsonToDto(message);
        mailService.mailSend(mailDto);
    }

    private MailDto convertJsonToDto(String mailDataJson) {
        ObjectMapper objectMapper = new ObjectMapper();

        MailDto mailDto = null;
        try {
            mailDto = objectMapper.readValue(mailDataJson, MailDto.class);
        } catch (JsonProcessingException e) {
            log.warn("json parsing error", e);
        }

        return mailDto;
    }
}

