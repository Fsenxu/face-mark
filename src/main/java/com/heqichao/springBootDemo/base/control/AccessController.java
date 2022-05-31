package com.heqichao.springBootDemo.base.control;

import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.SendService;
import com.heqichao.springBootDemo.base.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
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
    
    @RequestMapping(value = "/screen/accessOnline")
    public ResponeResult accessOnline() throws Exception{
    	Map<String, Object> map = sendService.testGroup();
    	return new ResponeResult(map);
    }
    
    
    @RequestMapping(value = "/screen/accessHistory")
    public ResponeResult accessHistory() throws Exception{
    	Map<String, Object> map = sendService.initConnect();
    	return new ResponeResult(map);
    }
    
    // 获取普通access token
    @RequestMapping(value = "/wechat/getToken")
    public ResponeResult getWechatToken() throws Exception{
    	return new ResponeResult();
    }
    

    



}
