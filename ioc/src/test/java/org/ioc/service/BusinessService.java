package org.ioc.service;

import org.ioc.annotation.SelfAutowired;
import org.ioc.annotation.SelfComponent;

@SelfComponent
public class BusinessService {

    @SelfAutowired
    private A a;

    public void doBusiness() {
        if (a != null) {
            System.out.println("注入成功");
        } else {
            System.out.println("注入失败");
        }
    }
}
