package com.heqichao.springBootDemo.base.service;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SendService {

    void sendSms(String sendUserId,String formUserId ,String context,boolean useQueue);

	Map<String, Object> testMeg();

	Map<String, Object> testGroup();
}
