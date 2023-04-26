package com.maldeniya.hodings.platformsystem.personal_sales.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document
@Data
public class PersonalSales {
    @Id
    private String id;
    private String userId;
    private long date;
    private Double scale;
    private Double percentage;

    public PersonalSales(String userId, long date, Double scale, Double percentage) {
        this.userId = userId;
        this.date = date;
        this.scale = scale;
        this.percentage = percentage;
    }
}
