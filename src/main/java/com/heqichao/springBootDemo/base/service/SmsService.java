package com.heqichao.springBootDemo.base.service;

import java.util.Map;

/**
 * Created by hqc on 2020/3/14.
 */
public interface SmsService {
    String REGION_ID = "cn-hangzhou";
    String DOMAIN ="dysmsapi.aliyuncs.com";
    String SIGN_NAME ="深圳圣斯尔电子";

    String TEMPLATE_CODE_ALARM_PUSH ="SMS_185846618";

    void send(String phoneNumber, String templateCode , Map<String,String> params) throws Exception;

}
