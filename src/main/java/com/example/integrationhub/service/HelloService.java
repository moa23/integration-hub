package com.example.integrationhub.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getGreeting() {
        return "Hello from Integration Hub!";
    }
}