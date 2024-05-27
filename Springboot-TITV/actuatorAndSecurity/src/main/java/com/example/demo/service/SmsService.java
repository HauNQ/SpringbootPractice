package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class SmsService implements MessageService{

    @Override
    public String sendMessage() {
        return "Send by sms";
    }
}
