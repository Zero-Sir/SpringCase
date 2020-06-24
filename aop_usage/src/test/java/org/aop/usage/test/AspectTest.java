package org.aop.usage.test;

import org.aop.usage.service.BusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AspectTest {

    @Autowired
    private BusinessService businessService;

    @Test
    public void testAspect() throws Exception {
        businessService.doBusiness();
    }
}
