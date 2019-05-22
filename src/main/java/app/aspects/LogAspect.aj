package app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public aspect LogAspect {
    @Pointcut("execution(* app.controllers..*(..))")
    public void controllers() {}

    @After("controllers()")
    public void logRequest(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature());
    }

    @Around("controllers()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature());
        return pjp.proceed(pjp.getArgs());
    }

    @Around("controllers()")
    public Object putCurrentUser(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("LOG LOGOVICH");
        return pjp.proceed(pjp.getArgs());
    }
}
