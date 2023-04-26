package com.maldeniya.hodings.platformsystem.personal_sales.repositories;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface PersonalScalesRepository extends MongoRepository<PersonalSales, String> {
    @Query("{$and: [{'userId': ?0}, " +
            "{'date': {$lte: ?1}}, " +
            "{'date': {$gte: ?2}}]}")
    List<PersonalSales> getPersonalSalesByQuery(String userId, long lowerBound, long upperBound);

    Optional<PersonalSales> findByIdAndUserId(String id, String userId);
}
