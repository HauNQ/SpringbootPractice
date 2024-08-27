package com.example.aspect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.aspect.service.CalculatorService.*(..))")
    public void pointcut() {}

//    @Before("pointcut()")
//    public void beforeAdd(JoinPoint joinPoint) {
//        System.out.println("Runing: "+ joinPoint.getSignature().getName());
//    }
//
//    @After("pointcut()")
//    public void afterAdd(JoinPoint joinPoint) {
//        System.out.println("Done: "+joinPoint.getSignature().getName());
//    }

//    @AfterReturning("execution (* com.example.aspect.service.CalculatorService.add(..)))")
//    public void afterReturningAdd(JoinPoint joinPoint) {
//        System.out.println("Done Returning");
//    }
//
//    @AfterThrowing("execution (* com.example.aspect.service.CalculatorService.add(..)))")
//    public void afterThrowingAdd(JoinPoint joinPoint) {
//        System.out.println("Error occurs");
//    }

//    @Around("execution (* com.example.aspect.service.CalculatorService.add(..)))")
//    public void aroundAdd(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Around");
//    }

    @Around("pointcut()")
    public Object aroundAddAndDivide(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Runing: "+ joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
//        Thread.sleep(100);
        System.out.println("Result: "+ result);
        long endTime = System.currentTimeMillis();
        System.out.println("Done: "+ joinPoint.getSignature().getName());
        System.out.println("Time: " + (endTime - startTime));
        return result;
    }



}
