package com.pizzeria.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    private long startTime;
    private long endTime;
    private long result;

    @Before("allServiceMethods()")
    public void logParams(JoinPoint jp) {
        log.info("EXECUTING METHOD " + jp.getSignature().getName() +
                " FROM " + jp.getSourceLocation().getWithinType().getName() +
                " WITH PARAMETERS: {}", jp.getArgs());
        this.startTime = LocalTime.now().getNano();
    }

    @Pointcut("within(com.lab18.services.*)")
    public void allServiceMethods() {
    }

    @After("allServiceMethods()")
    public void showExecutionTime(JoinPoint jp) {
        this.endTime = LocalTime.now().getNano();
        this.result = (endTime - startTime) / 1000000;
        log.info("METHOD: " + jp.getSignature().getName() +
                " FROM: " + jp.getSourceLocation().getWithinType().getName() +
                " – EXECUTION COMPLETED, SPEND TIME: " + result + " ms");
    }
}
