package com.heqichao.springBootDemo.base.listener;

import com.heqichao.springBootDemo.base.config.PropertiesConfig;
import com.heqichao.springBootDemo.megprotocol.SDKInitUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by heqichao on 2018-7-15.
 */
@Component
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class MyApplicationRunningListener implements ApplicationRunner {

    @Autowired
    private  PropertiesConfig propertiesConfig;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        initTask();
        logger.info("应用启动成功："+new Date());
    }

    private void initTask(){
    	SDKInitUnit.sdkInit();
        logger.info("environment ："+propertiesConfig.getSystemEnviromment());
    }

}
