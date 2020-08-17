package com.victolee.fakemsa.service;

import com.victolee.fakemsa.dto.MailDto;
import com.victolee.fakemsa.entity.MailLogEntity;
import com.victolee.fakemsa.repository.MailLogRepository;
import com.victolee.fakemsa.util.MailHandler;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private MailLogRepository mailLogRepository;
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

            // 메일 발송
            mailHandler.send();

            // 메일 발송 로그 기록
            setLog(this.createLogEntity(mailDto));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 메일발송 로그 기록
     */
    private void setLog(MailLogEntity mailLogEntity) {
        mailLogRepository.save(mailLogEntity);
    }

    /*
     * 로그 엔티티 생성
     */
    private MailLogEntity createLogEntity(MailDto mailDto) {
        MailLogEntity mailLogEntity = MailLogEntity.builder()
                .address(mailDto.getAddress())
                .title(mailDto.getTitle())
                .message(mailDto.getMessage())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        return mailLogEntity;
    }
}