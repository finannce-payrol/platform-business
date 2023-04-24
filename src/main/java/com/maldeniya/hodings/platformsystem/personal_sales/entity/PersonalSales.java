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
    private Instant date;
    private Double scale;
    private Double percentage;
}
