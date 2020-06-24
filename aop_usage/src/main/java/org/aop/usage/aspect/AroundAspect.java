package org.aop.usage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundAspect {

    @Pointcut("within(org.aop.usage.service.BusinessService)")
    public void pointcut(){}

    @Around("pointcut()")
    public void aroundAspect(ProceedingJoinPoint joinPoint) {
        // 连接点方法执行前，相当于前置通知
        System.out.println("around: 业务方法执行前");

        try {
            // 业务方法执行
            joinPoint.proceed();

            // 连接点方法执行后，相当于后置通知
            System.out.println("around: 业务方法执行后");
        } catch (Throwable throwable) {
            // 连接点方法执行后，相当于后置通知
            System.out.println("around: 业务方法执行出现异常");
        } finally {
            System.out.println("around: 切面执行完成");
        }
    }
}
