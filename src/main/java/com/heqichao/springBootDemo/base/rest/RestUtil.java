package com.heqichao.springBootDemo.base.rest;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by heqichao on 2018-8-18.
 */
public class RestUtil {
    /**
     * 向目的URL发送post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  ResultVO
     */
    public static RestResult sendPostRequest(String url, MultiValueMap<String, String> params){
        return sendPostRequest(url, params, RestResult.class);
    }

    public static <T> T sendPostRequest(String url, MultiValueMap<String, String> params,Class<T> className){
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<T> response = client.exchange(url, method, requestEntity,className);
        return response.getBody();
    }

    /**
     * 向目的URL发送get请求
     * @param url       目的url
     * @param params    发送的参数
     * @return  ResultVO
     */
    public static <T> T sendGetRequest(String url, Map<String, String> params, Class<T> className){
        RestTemplate client = new RestTemplate();
        return client.getForObject(url,className,params);
    }
}
