package com.example.demo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Component
public class EmailService implements MessageService{

    @Override
    public String sendMessage() {
        return "Send by email";
    }
}
