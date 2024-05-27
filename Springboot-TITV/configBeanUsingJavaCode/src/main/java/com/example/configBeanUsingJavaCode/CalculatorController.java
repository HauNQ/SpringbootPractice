package com.example.configBeanUsingJavaCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private  Calculator calculator;

    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }



    @GetMapping("/sqrt")
    public double sqrt(@RequestParam("val") double x) {
       return calculator.calSqrt(x);
    }

    @GetMapping("/square")
    public double square(@RequestParam("val") double x) {
        return calculator.square(x);
    }
}
