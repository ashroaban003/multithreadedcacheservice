package com.example.multithreadedcacheservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multithreadedcacheservice.models.User;
import com.example.multithreadedcacheservice.service.userService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService userService;

    @GetMapping("/")
    public String getMethodName(@RequestParam(required = false) String param) {
        return "nah ! I win";
    }

    @GetMapping("/{id}")
    ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity<>(userService.getuser(id),HttpStatus.OK);
    }
    
    @PostMapping("/create")
    ResponseEntity<User> postMethodName(@RequestBody User user) {
        //TODO: process POST request
        
        return new ResponseEntity<>(userService.saveuser(user),HttpStatus.ACCEPTED);
    }
    

    
}
