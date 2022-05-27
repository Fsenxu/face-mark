package com.heqichao.springBootDemo.base.util.gd;

import com.heqichao.springBootDemo.base.rest.RestUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 高德api
 * Created by heqichao on 2019-8-10.
 */
public class GaoDeAPI {
    private final static String KEY="ca7ed17dcdaacee217cadcba3ed86286";

    /**
     * 逆地理编码API服务地址
     * https://lbs.amap.com/api/webservice/guide/api/georegeo
     */
    private final static String REGEO_API_URL="https://restapi.amap.com/v3/geocode/regeo";

    /**
     * 根据坐标获取地理数据
     * @param location
     * @return
     */
    public static GaoDeRegeocodesResult getGaoDeRegeocodesResult(String location){
        if(StringUtil.isNotEmpty(location)){
            Map<String,String> map =new HashMap<String,String>();
            map.put("key",KEY);
            map.put("location",location);
            String url =REGEO_API_URL+"?key={key}&location={location}";
            return RestUtil.sendGetRequest(url,map,GaoDeRegeocodesResult.class);
        }
        return null;
    }


    public static void main(String[] args) {
        GaoDeRegeocodesResult r =getGaoDeRegeocodesResult("91.128099,29.660249");
        System.out.println(r);
    }
}
