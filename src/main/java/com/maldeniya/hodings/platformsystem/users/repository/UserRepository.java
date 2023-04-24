package com.maldeniya.hodings.platformsystem.users.repository;

import com.maldeniya.hodings.platformsystem.users.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
