package com.imooc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2019/10/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        logger.debug("debug...");
        logger.info("info....");
    }

    @Test
    public void test2(){
        log.info("slf4j....info");
        log.debug("slf4j....debug");
        log.error("slf4j....error");
    }

    @Test
    public void test3(){
        String name = "imooc";
        String password = "123456";
        log.info("info#######name:{},password:{}",name,password);
        log.warn("warn#######name:{},password:{}",name,password);
        log.debug("debug#######name:{},password:{}",name,password);
        log.error("error#######name:{},password:{}",name,password);
    }
}
