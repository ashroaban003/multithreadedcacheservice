package com.example.multithreadedcacheservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multithreadedcacheservice.models.User;

public interface userRepository extends JpaRepository<User,Long> {

    
}
