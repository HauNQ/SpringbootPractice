package com.example.configBeanUsingJavaCode;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Calculation {

    @Bean
    public Calculator getCalculator() {
        return new Calculator();
    }
}
