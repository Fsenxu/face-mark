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
    @RequestMapping(value = "/person/query")
    public ResponeResult personQuery(@RequestBody Map map) throws Exception{
    	Map<String, Object> res = sendService.personQuery(map);
    	
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
    @RequestMapping(value = "/sdk/initConnect")
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
