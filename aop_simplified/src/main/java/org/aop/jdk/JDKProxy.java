package org.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxy implements InvocationHandler {

    private Object targetObject;

    public JDKProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before 前置通知");
        Object result = null;

        try {
            result = method.invoke(targetObject, args);

            System.out.println("afterReturning 返回通知");
        } catch (Throwable throwable) {
            System.out.println("afterThrowing 异常通知");
        } finally {
            System.out.println("after 后置通知");
        }

        return result;
    }
}
