package com.aaa.service.claim.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.aaa.service.claim.measure.MetricsLog;


@Aspect
@Component
public class MetricAspect {

	@Around("@annotation(Metrics)")
	public Object metrics(ProceedingJoinPoint joinPoint) throws Throwable {
		MetricsLog metrics = new MetricsLog(joinPoint.toShortString());
		metrics.start();
	    Object proceed = joinPoint.proceed();
	    metrics.stop();
	    return proceed;
	}
}
