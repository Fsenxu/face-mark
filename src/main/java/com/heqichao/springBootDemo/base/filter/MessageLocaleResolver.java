package com.heqichao.springBootDemo.base.filter;

import com.heqichao.springBootDemo.base.util.ServletUtil;
import com.heqichao.springBootDemo.base.util.StringUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by heqichao on 2019-7-6.
 */

@Configuration
public class MessageLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String lang = ServletUtil.getSessionLanguage();
        if(StringUtil.isEmpty(lang)){
            lang ="cn";//默认语言
        }
        return new Locale(lang);
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
