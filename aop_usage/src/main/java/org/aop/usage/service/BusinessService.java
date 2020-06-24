package org.aop.usage.service;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    /**
     * 业务处理方法
     */
    public void doBusiness() {
        System.out.println("处理业务。。。。。。");
    }
}
