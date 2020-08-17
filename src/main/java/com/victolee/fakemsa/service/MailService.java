package com.victolee.fakemsa.service;

import com.victolee.fakemsa.dto.MailDto;
import com.victolee.fakemsa.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "dntmddlekd48@gmail.com";

    public void mailSend(MailDto mailDto) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(mailDto.getAddress());
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject(mailDto.getTitle());
            // HTML Layout
            String htmlContent = "<p>" + mailDto.getMessage() + "<p>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}