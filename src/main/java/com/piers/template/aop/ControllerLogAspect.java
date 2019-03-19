package com.piers.template.aop;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.piers.template.data.response.BaseResponse;
import com.piers.template.utils.Const;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
/**
 * @author piers
 *
 */
public class ControllerLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("execution(* com.piers.template.controller.*.*(..))")
    public BaseResponse handleControllerMethod(ProceedingJoinPoint pjp) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        BaseResponse baseResponse = null;
        try {
            logger.info("Start Controller: " + pjp.getSignature() + " Param:" + Lists.newArrayList(pjp.getArgs()).toString());
            baseResponse = (BaseResponse) pjp.proceed(pjp.getArgs());
            logger.info("End Controller: " + pjp.getSignature() + "， Response:" + baseResponse.toString());
            logger.info("Cost: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "(ms).");
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage());
            baseResponse = new BaseResponse(Const.ERR_EXCEPTION, e.getMessage());
        } catch (Throwable throwable) {
            baseResponse = handlerException(pjp, throwable);
        }
        return baseResponse;
    }

    private BaseResponse handlerException(ProceedingJoinPoint pjp, Throwable e) {
        logger.error("Exception{Method:" + pjp.getSignature() + ", Param:" + pjp.getArgs() + ", Exception:" + e.getMessage() + "}", e);
        return new BaseResponse(Const.ERR_EXCEPTION, e.getMessage());
    }
}
