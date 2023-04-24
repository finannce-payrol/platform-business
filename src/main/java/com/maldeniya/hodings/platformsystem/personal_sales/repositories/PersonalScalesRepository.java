package com.maldeniya.hodings.platformsystem.personal_sales.repositories;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface PersonalScalesRepository extends MongoRepository<PersonalSales, String> {
    @Query("{$and: [{'userId': ?0}, " +
            "{'date': {$lte: ?1}}, " +
            "{'date': {$gte: ?2}}]}")
    List<PersonalSales> getPersonalSalesByQuery(String userId, Instant lowerBound, Instant upperBound);
}
