package com.github.yingzhuo.spring.auto.datasource.composite.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DefaultDataSourceSwitchAdvice extends DataSourceSwitchAdviceSupport {

    @Override
    @Around("execution(public * *.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.around(joinPoint);
    }

}
