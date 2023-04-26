package com.maldeniya.hodings.platformsystem.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoString;

    @Bean
    public MongoClient getMongoClient(){
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoString))
                .applyToSocketSettings(builder -> builder.connectTimeout(30000, TimeUnit.MILLISECONDS))
                .build();

        return MongoClients.create(settings);

    }
}
