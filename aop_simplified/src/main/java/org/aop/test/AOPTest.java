package org.aop.test;

import org.aop.cglib.CGLibProxy;
import org.aop.jdk.JDKProxy;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AOPTest {
    public static void main(String[] args) {
        // JDK动态代理
        InvocationHandler handler = new JDKProxy(new Service());
        IService service = (IService) Proxy.newProxyInstance(AOPTest.class.getClassLoader(), new Class[]{IService.class}, handler);
        service.doBusiness(1);

        // CGLib动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service.class);
        enhancer.setCallback(new CGLibProxy());
        service = (Service) enhancer.create();
        service.doBusiness(2);
    }
}
