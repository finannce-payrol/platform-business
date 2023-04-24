package com.maldeniya.hodings.platformsystem.users.services;

import com.maldeniya.hodings.platformsystem.users.entity.User;
import com.maldeniya.hodings.platformsystem.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(String userId){
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("entity not found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
