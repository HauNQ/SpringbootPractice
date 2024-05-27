package com.example.demo.rest;

import com.example.demo.service.EmailService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Messagecontrolller {
    private MessageService messageService;

    @Autowired
    public Messagecontrolller(@Qualifier("smsService") MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/send")
    public String sendMessage(){
        return this.messageService.sendMessage();
    }

}
