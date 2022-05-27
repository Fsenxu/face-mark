package com.heqichao.springBootDemo.base.service;

/**
 * Created by hqc on 2020/3/14.
 */
public interface EmailService {

    void sendSupportMail1(String subject ,String htmlText) ;

    void sendErrorMail(String subject,String desc,Exception ex);

    void sendMail(String receiver , String subject ,String htmlText) throws Exception;
}
