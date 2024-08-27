package com.example.demo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class ZaloService implements MessageService{

    @Override
    public String sendMessage() {
        return "Send by zalo";
    }
}
