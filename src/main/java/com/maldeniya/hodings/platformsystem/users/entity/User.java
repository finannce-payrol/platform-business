package com.maldeniya.hodings.platformsystem.users.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String address;
    private Double age;

    public User(String username, String address, Double age) {
        this.username = username;
        this.address = address;
        this.age = age;
    }
}
