package com.maldeniya.hodings.platformsystem.users.controllers;

import com.maldeniya.hodings.platformsystem.users.entity.User;
import com.maldeniya.hodings.platformsystem.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }
    @GetMapping(value = "/{userId}")
    public ResponseEntity getUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
