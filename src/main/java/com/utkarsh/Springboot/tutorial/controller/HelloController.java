package com.utkarsh.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${message.config}")
    private String welcomeMessage;

    @GetMapping("/hello")
    public String helloWorld(){
        return welcomeMessage;
    }
}
