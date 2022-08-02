package com.heqichao.springBootDemo.base.service;

import java.util.Map;

/**
 * Created by heqichao on 2018-2-12.
 */
public interface SendService {

    void sendSms(String sendUserId,String formUserId ,String context,boolean useQueue);

    /**
     * 初始化设备连接
     * @return
     * @throws Exception
     */
	Map<String, Object> initConnect() throws Exception;


	/**
	 * 添加人员底库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> personAdd(Map map) throws Exception;
	
	/**
	 * 更新人员底库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> personUpdate(Map map) throws Exception;

	/**
	 * 查询人员底库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> personQuery(Map map) throws Exception;

	/**
	 * 订阅报警
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> initAlarm(Map map) throws Exception;

	Map<String, Object> testGroup(Map map) throws Exception;

	/**
	 * 分组操作
	 * @param map
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> groupManager(Map map, int type) throws Exception;

	/**
	 * 删除人员地库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> personDelete(Map map) throws Exception;

	/**
	 * 时间计划
	 * @param map
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> scheduleManager(Map map, int type) throws Exception;

	/**
	 * 功能配置
	 * @param map
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> monitorManager(Map map, int type) throws Exception;

}
