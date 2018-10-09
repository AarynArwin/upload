package com.chengnanhuakai.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName MysqlConfig
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/9/29 15:07
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class MysqlConfig {

    private String url;
}
