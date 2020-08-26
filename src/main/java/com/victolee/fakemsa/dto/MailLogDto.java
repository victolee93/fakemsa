package com.victolee.fakemsa.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailLogDto {
    private String address;
    private String title;
    private String message;
    private String date;

    @Builder
    public MailLogDto(String address, String title, String message, String date) {
        this.address = address;
        this.title = title;
        this.message = message;
        this.date = date;
    }
}
