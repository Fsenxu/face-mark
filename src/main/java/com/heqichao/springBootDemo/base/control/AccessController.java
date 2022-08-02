package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.SendService;
import com.heqichao.springBootDemo.base.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//@RestController == @Controller + @ResponseBody
@RestController
@RequestMapping(value = "/face")
public class AccessController extends BaseController{


	@Autowired
	SendService sendService;
	
    @RequestMapping(value = "/screen/access")
    public ResponeResult access(@RequestBody Map map) throws Exception{
    	String aIp =StringUtil.getStringByMap(map,"aIp");
         Map<String, Object> res = new HashMap<String, Object>();
         res.put("aIp", aIp);
         res.put("token", "123");
         res.put("total", 123);
         return new ResponeResult(res);
    }
    
    @RequestMapping(value = "/person/add")
    public ResponeResult personAdd(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.personAdd(map);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/person/update")
    public ResponeResult personUpdate(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.personUpdate(map);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/person/delete")
    public ResponeResult personDelete(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.personDelete(map);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/person/query")
    public ResponeResult personQuery(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.personQuery(map);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/group/query")
    public ResponeResult groupQuery(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.groupManager(map,3);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/group/add")
    public ResponeResult groupAdd(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.groupManager(map,0);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/group/update")
    public ResponeResult groupUpdate(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.groupManager(map,1);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/group/delete")
    public ResponeResult groupDelete(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.groupManager(map,2);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/schedule/add")
    public ResponeResult scheduleAdd(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.scheduleManager(map,0);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/schedule/update")
    public ResponeResult scheduleUpdate(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.scheduleManager(map,1);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/schedule/delete")
    public ResponeResult scheduleDelete(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.scheduleManager(map,2);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/schedule/query")
    public ResponeResult scheduleQuery(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.scheduleManager(map,3);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/monitor/set")
    public ResponeResult monitorSet(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.monitorManager(map,1);
    	
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/monitor/query")
    public ResponeResult monitorQuery(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.monitorManager(map,3);
    	
    	return new ResponeResult(res);
    }
    
    @RequestMapping(value = "/device/initAlarm")
    public ResponeResult initAlarm(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.initAlarm(map);
    	return new ResponeResult(res);
    }
    
    
    @RequestMapping(value = "/screen/accessHistory")
    public ResponeResult accessHistory(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.testGroup(map);
    	return new ResponeResult(res);
    }
    @RequestMapping(value = "/device/initConnect")
    public ResponeResult initConnect() throws Exception{
    	Map<String, Object> map = sendService.initConnect();
    	return new ResponeResult(map);
    }
    
    // 获取普通access token
    @RequestMapping(value = "/wechat/getToken")
    public ResponeResult getWechatToken() throws Exception{
    	return new ResponeResult();
    }
    

    



}
