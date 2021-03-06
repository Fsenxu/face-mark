package com.heqichao.springBootDemo.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件信息 通过value注解注入单个属性
 * Created by heqichao on 2018-2-12.
 */
@Component
public class PropertiesConfig {

    @Value("${spring.profiles.active}")
    private String systemEnviromment;

    @Value("${server.servlet.contextPath}")
    private String projectName;

    @Value("${database.isUsingES}")
    private String isUsingES;

    public String getSystemEnviromment() {
        return systemEnviromment;
    }

    public void setSystemEnviromment(String systemEnviromment) {
        this.systemEnviromment = systemEnviromment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIsUsingES() {
        return isUsingES;
    }

    public void setIsUsingES(String isUsingES) {
        this.isUsingES = isUsingES;
    }

    /**
     * 判断是否为生产环境
     * @return
     */
    public boolean isProdEnv(){
        return "prod".equalsIgnoreCase(systemEnviromment);
    }

    /**
     * 判断是否使用ES
     * @return
     */
    public boolean isUsingES(){
        return "true".equalsIgnoreCase(isUsingES);
    }
}
