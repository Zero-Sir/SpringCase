package org.aop.usage.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckAspect {

    /**
     * 切入点：主要是声明连接点
     */
    @Pointcut("within(org.aop.usage.service.BusinessService)")
    public void methodPointcut(){}

    @Before("methodPointcut()")
    public void beforeAdvice() {
        System.out.println("执行业务方法前");
    }

    @After("methodPointcut()")
    public void afterAdvice() {
        System.out.println("执行业务方法后");
    }

    @AfterThrowing("methodPointcut()")
    public void afterThrowing() {
        System.out.println("业务方法抛出错误后");
    }

    /**
     * 在 After通知之前执行
     */
    @AfterReturning("methodPointcut()")
    public void afterReturning() {
        System.out.println("业务方法return后");
    }
}
