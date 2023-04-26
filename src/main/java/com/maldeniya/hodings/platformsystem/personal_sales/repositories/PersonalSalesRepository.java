package com.maldeniya.hodings.platformsystem.personal_sales.repositories;

import com.maldeniya.hodings.platformsystem.personal_sales.entity.PersonalSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonalSalesRepository extends MongoRepository<PersonalSales, String> {
    @Query("{$and: [{'userId': ?0}, " +
            "{'date': {$gte: ?1}}, " +
            "{'date': {$lte: ?2}}]}")
    List<PersonalSales> getPersonalSalesByQuery(String userId, long lowerBound, long upperBound);

    Optional<PersonalSales> findByIdAndUserId(String id, String userId);
}
