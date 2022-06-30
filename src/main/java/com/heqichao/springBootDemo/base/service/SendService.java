package com.heqichao.springBootDemo.base.service;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SendService {

    void sendSms(String sendUserId,String formUserId ,String context,boolean useQueue);

	Map<String, Object> initConnect() throws Exception;


	/**
	 * 添加人员底库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> personAdd(Map map) throws Exception;
	
	Map<String, Object> personUpdate(Map map) throws Exception;

	Map<String, Object> personQuery(Map map) throws Exception;

	Map<String, Object> initAlarm(Map map) throws Exception;

	Map<String, Object> testGroup(Map map) throws Exception;

}
