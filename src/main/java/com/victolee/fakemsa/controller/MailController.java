package com.victolee.fakemsa.controller;

import com.victolee.fakemsa.dto.MailDto;
import com.victolee.fakemsa.service.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MailController {
    private final KafkaProducer producer;

    @GetMapping("/mail")
    public String dispMail() {
        return "mail";
    }

    @PostMapping(value = "/mail")
    @ResponseBody
    public String sendMessageToKafkaTopic(MailDto mailDto) {
        this.producer.sendMessage(mailDto);

        return "success";
    }
}