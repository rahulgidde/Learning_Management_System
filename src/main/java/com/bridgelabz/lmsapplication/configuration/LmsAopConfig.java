package com.bridgelabz.lmsapplication.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LmsAopConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.bridgelabz.lmsapplication.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info("Check before Method Call");
        logger.info("Allowed execution for {}", joinPoint);
    }

    @After("execution(* com.bridgelabz.lmsapplication.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("Check after Method Call ");
        logger.info("Allowed execution for {}", joinPoint);
    }
}
