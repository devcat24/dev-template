package com.github.devcat24.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class AOPProfiler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AOPProfiler.class);

    //@Before("execution(* com.github.devcat24.mvc.repo.hr.Emp01Repo.*(..))")
    //public void lookDatabaseAutoConfiguration(JoinPoint jointPoint) throws Throwable{
    //    String methodName = jointPoint.getSignature().getName();
    //    Object [] args = jointPoint.getArgs();
    //
    //    logger.info("===================spring aop ] "+methodName+" -->: " + new Date());
    //    for(Object arg: args){
    //        logger.info("spring aop ] "+arg.toString());
    //    }
    //}
    //
    //@After("execution(* com.github.devcat24.mvc.repo.hr.Emp01Repo.*(..))")
    //public void logAfter(JoinPoint jointPoint) throws Throwable{
    //    String methodName = jointPoint.getSignature().getName();
    //    logger.info("spring aop ] " + methodName+" <--: " + new Date());
    //    logger.info("spring aop ] =============================<><");
    //
    //}

    @Around("execution(* com.github.devcat24.mvc.repo.hr.Emp01Repo.*(..))")
    public Object logJointPoint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();

        Object [] args = proceedingJoinPoint.getArgs();
        if(args != null && args.length > 0) {
            for (Object arg : args) {
                logger.info("spring aop - params :  " + arg.toString());
            }
        }
        Object rtnObj = null;
        try {
            rtnObj = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }
        logger.info("spring aop - returns with =============================: "+ (rtnObj != null ? rtnObj.toString() :"" ) );
        return rtnObj;

    }


}
