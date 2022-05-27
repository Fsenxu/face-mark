package com.heqichao.springBootDemo.base.exception;

import com.heqichao.springBootDemo.base.filter.LocaleMessage;
import com.heqichao.springBootDemo.base.param.RequestContext;
import com.heqichao.springBootDemo.base.param.ResponeResult;
import com.heqichao.springBootDemo.base.service.EmailService;
import com.heqichao.springBootDemo.base.util.JsonUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by heqichao on 2018-2-20.
 */
//@RestControllerAdvice  则可以不用添加ResponseBody
@ControllerAdvice
public class ResponeExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());
    private String DEFAULT_ERROR_MSG ="DEFAULT_ERROR_MSG";
    @Autowired
    private ExceptionMap exceptionMap;
    @Autowired
    private LocaleMessage messageSource;

    @Autowired
    private EmailService emailService;

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponeResult errorHandler(Exception ex) {
        StringBuilder stringBuilder =new StringBuilder("<div>请求信息:</div>");
        try{
            HttpServletRequest request = RequestContext.getContext().getRequest();
            if(request!=null){
                stringBuilder.append("<div>&emsp; method : ").append(request.getMethod()).append("</div>")
                        .append("<div>&emsp; url : ").append(request.getRequestURL().toString()).append("</div>")
                        .append("<div>&emsp; addr : ").append(request.getLocalAddr().toString()).append("</div>")
                        .append("<div>&emsp; header : ").append("</div>");
                Enumeration<String> headers = request.getHeaderNames();
                while (headers!=null && headers.hasMoreElements()){
                    String name = headers.nextElement();
                    stringBuilder.append("<div>&emsp;&emsp; "+name+" : ").append(request.getHeader(name)).append("</div>");
                }
                Map map = RequestContext.getContext().getParamMap();
                stringBuilder.append("<div>&emsp; param : ").append(JsonUtil.getJsonString(map)).append("</div>");
            }
        }catch (Exception e){
            logger.error("获取请求信息异常,"+stringBuilder.toString(),e);
        }
        logger.error(stringBuilder.toString(),ex);
        emailService.sendErrorMail("系统报错",stringBuilder.toString(),ex);

        return new ResponeResult(false,messageSource.getMessage(DEFAULT_ERROR_MSG),"errorMsg");
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ResponeException.class)
    public ResponeResult myErrorHandler(ResponeException ex) {
        logger.error("System_ResponeException:",ex);
        String err=messageSource.getMessage(DEFAULT_ERROR_MSG);
        if(StringUtil.isNotEmpty(ex.getMsg())){
            err=ex.getMsg();
        }
        ResponeResult responeResult= new ResponeResult(false,err,"errorMsg");
        if(StringUtil.isNotEmpty((String) exceptionMap.getExceptionMap().get(ex.getMsg()))){
            responeResult.setMessage((String) exceptionMap.getExceptionMap().get(ex.getMsg()));
        }
        return responeResult;
    }
}
