package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

  @Before("@annotation(com.example.aop.LogMethod)")
  public void logMethodExecution(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    String params = Arrays.toString(joinPoint.getArgs());
    System.out.println("Method [" + method + "] gets called with parameters " + params);
  }

  @Around("@annotation(com.example.aop.LogMethod)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long duration = System.currentTimeMillis() - startTime;
    System.out.println("Execution took [" + duration + "ms]");
    return proceed;
  }

   @AfterReturning(pointcut = "@annotation(com.example.aop.LogMethod)", returning = "result")
  public void logMethodReturn(JoinPoint joinPoint, Object result) {
    String method = joinPoint.getSignature().getName();
    System.out.println("Method [" + method + "] returns with value " + result);
  }

  @AfterThrowing(pointcut = "@annotation(com.example.aop.LogMethod)", throwing = "exception")
  public void logMethodException(JoinPoint joinPoint, Throwable exception) {
    String method = joinPoint.getSignature().getName();
    System.out.println("Method [" + method + "] threw exception: " + exception.getMessage());
  }

}
