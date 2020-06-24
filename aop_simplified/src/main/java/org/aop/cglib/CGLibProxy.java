package org.aop.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before 前置通知");

        Object result = null;

        try {
            result = methodProxy.invokeSuper(obj, args);

            System.out.println("afterReturning 返回通知");
        } catch (Throwable throwable) {
            System.out.println("afterThrowing 异常通知");
        } finally {
            System.out.println("after 后置通知");
        }

        return result;
    }
}
