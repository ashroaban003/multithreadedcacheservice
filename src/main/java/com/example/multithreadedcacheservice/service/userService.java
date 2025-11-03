package com.example.multithreadedcacheservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.multithreadedcacheservice.models.User;
import com.example.multithreadedcacheservice.repository.userRepository;

public class userService {
    @Autowired
    private userRepository userRepository;

    public User getuser(Long id){
        return userRepository.findById(id).orElseThrow();
    }
    public User saveuser(User user){
        return userRepository.save(user);
    }
}
