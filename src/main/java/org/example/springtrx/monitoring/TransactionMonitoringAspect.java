package org.example.springtrx.monitoring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionMonitoringAspect {
    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionStart(JoinPoint joinPoint) {
        System.out.println("Transaction started for method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactionEnd(JoinPoint joinPoint) {
        System.out.println("Transaction completed for method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "@annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
    public void logTransactionError(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Transaction failed for method: " + joinPoint.getSignature().getName() + " with exception: " + ex.getMessage());
    }
}
