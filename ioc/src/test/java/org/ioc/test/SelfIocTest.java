package org.ioc.test;

import org.ioc.context.SelfPathApplicationContext;
import org.ioc.service.BusinessService;

public class SelfIocTest {

    public static void main(String[] args) throws Exception {
        SelfPathApplicationContext context = new SelfPathApplicationContext("org.ioc.service");
        BusinessService service = (BusinessService) context.getBean("businessService");
        service.doBusiness();
    }
}
