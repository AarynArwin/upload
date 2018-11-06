package com.chengnanhuakai.upload.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfiguration
 * @Description 自定义 Filter 加入过滤链
 * @Author Aaryn
 * @Date 2018/10/8 14:35
 * @Version 1.0
 */
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter");
        registration.setOrder(6);
        return registration;
    }

    /**
     * 创建字符过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean charsetFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        // 定义字符编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        // 设置编码格式为UTF-8
        characterEncodingFilter.setEncoding("UTF-8");
        // 将过滤器注册
        filterRegistrationBean.setFilter(characterEncodingFilter);
        // 创建过滤规则集合
        List<String> patterns = new ArrayList<>();
        // 设置过滤规则为全部过滤
        patterns.add("/*");
        // 加入过滤规则
        filterRegistrationBean.setUrlPatterns(patterns);

        return filterRegistrationBean;
    }
}
