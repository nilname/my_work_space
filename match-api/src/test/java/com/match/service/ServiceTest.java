package com.match.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-test-service.xml")
public class ServiceTest {

    @Resource
    private LoanChannelService loanChannelService;

    @Test
    public void test(){
        loanChannelService.findAll();
    }
}
