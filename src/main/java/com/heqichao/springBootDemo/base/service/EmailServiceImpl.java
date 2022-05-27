package com.heqichao.springBootDemo.base.service;

import com.heqichao.springBootDemo.base.config.PropertiesConfig;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by hqc on 2019/12/30.
 */
@Service
public class EmailServiceImpl implements EmailService{
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    // 获取配置文件的username
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.receiver}")
    private String receiver;
    @Autowired
    private PropertiesConfig propertiesConfig;

    @Override
    public void sendSupportMail1(String subject , String htmlText) {
        try{
            String env="";
            if("DEV".equalsIgnoreCase(propertiesConfig.getSystemEnviromment())){
                env="测试环境";
            }else if("PROD".equalsIgnoreCase(propertiesConfig.getSystemEnviromment())){
                env="生产环境";
            }
            subject = "["+env+"] "+subject;
            sendMail(receiver,subject,htmlText);
        } catch (Exception e) {
            logger.error("发送异常邮件失败",e);
        }
    }

    @Override
    public void sendErrorMail(String subject, String desc, Exception ex){
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append("<table  border=\"1\">");
        stringBuilder.append("<tr>").append("<th>描述</th><th>调用栈</th><th>异常原因</th></tr>");
        stringBuilder.append("<tr><td>").append(desc).append("</td><td>");
        if(ex.getStackTrace()!=null && ex.getStackTrace().length>0){
            for(StackTraceElement stackTraceElement : ex.getStackTrace()){
                stringBuilder.append(stackTraceElement.toString()).append("\n");
            }
        }
        stringBuilder.append("</td><td>").append(ex.getMessage()).append("</td></tr>");
        stringBuilder.append("</table>");
        sendSupportMail1(subject,stringBuilder.toString());
    }

    @Override
    public void sendMail(String receiver, String subject, String htmlText)  throws Exception{
        if(StringUtil.isEmpty(receiver) || StringUtil.isEmpty(username) ){
            return;
        }
        String[] toUsers = receiver.split(";");
        for(String toUser :toUsers){
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(username);
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText("<html><body>"+htmlText+"</body></html>", true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
        }
    }
}
