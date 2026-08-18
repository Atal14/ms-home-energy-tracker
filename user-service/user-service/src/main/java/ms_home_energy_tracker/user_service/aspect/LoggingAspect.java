package ms_home_energy_tracker.user_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("execution(* ms_home_energy_tracker.user_service.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Called service method: {} with arguments: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(value = "serviceMethods()", returning = "results")
    public void logAfterReturning(JoinPoint joinPoint, Object results) {
        log.info("Service method: {} returned with results: {}", joinPoint.getSignature().getName(), results);
    }

}
