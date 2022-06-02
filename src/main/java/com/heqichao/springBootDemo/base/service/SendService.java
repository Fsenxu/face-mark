package com.heqichao.springBootDemo.base.service;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SendService {

    void sendSms(String sendUserId,String formUserId ,String context,boolean useQueue);

	Map<String, Object> initConnect() throws Exception;

	Map<String, Object> testGroup() throws Exception;

	Map<String, Object> personAdd(Map map) throws Exception;

	Map<String, Object> personQuery(Map map) throws Exception;

	Map<String, Object> initAlarm(Map map) throws Exception;
}
