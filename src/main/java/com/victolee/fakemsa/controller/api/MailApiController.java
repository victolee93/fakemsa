package com.victolee.fakemsa.controller.api;

import com.victolee.fakemsa.dto.MailDto;
import com.victolee.fakemsa.dto.MailLogDto;
import com.victolee.fakemsa.service.MailService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "메일 발송 API")
@RequestMapping("/v1/api/mail")
@AllArgsConstructor
public class MailApiController {
    private final MailService mailService;

    @ApiOperation(value = "mail log", notes = "메일 발송 로그 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
    @GetMapping(value = "/log")
    public List<Map<String, String>> getMailLog(
            @ApiParam(value = "날짜", required = true, example = "2020-08-23") @RequestParam String searchDate) {

        List<MailLogDto> mailLogDtos = mailService.getSendLog(searchDate);
        List<Map<String, String>> mailLogList = new ArrayList<>();

        for (MailLogDto mailLogDto : mailLogDtos) {
            Map<String, String> result = new HashMap<>();
            result.put("address-to", mailLogDto.getAddress());
            result.put("title", mailLogDto.getTitle());
            result.put("message", mailLogDto.getMessage());
            result.put("date", mailLogDto.getDate());
            mailLogList.add(result);
        }

        return mailLogList;
    }


    @ApiOperation(value = "mail send", notes = "메일 발송")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
    @PostMapping("/")
    public Map<String, String> sendMail(
            @ApiParam(value = "보내는 사람", required = true, example = "foo@example.com") @RequestParam String toAddress,
            @ApiParam(value = "제목", required = true, example = "This is title") @RequestParam String title,
            @ApiParam(value = "날짜", required = true, example = "This is content") @RequestParam String message) {

        MailDto mailDto = MailDto.builder()
                .address(toAddress)
                .title(title)
                .message(message)
                .build();
        Boolean sendResult = mailService.mailSend(mailDto);

        Map<String, String> result = new HashMap<>();
        if (sendResult == true) {
            result.put("code", "200");
            result.put("message", "mail send success");
        } else {
            result.put("code", "500");
            result.put("message", "mail send fail");
        }

        return result;
    }
}
