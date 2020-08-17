package com.victolee.fakemsa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Builder
@Data
@AllArgsConstructor
@Document
public class MailLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    final String id;

    @Field
    String address;

    @Field
    String title;

    @Field
    String message;

    @Field
    @CreatedDate
    String date;
}
