package org.aop.test;

public class Service implements IService{


    @Override
    public void doBusiness(int a) {
        System.out.println("执行业务方法 a=" + a);
    }
}
