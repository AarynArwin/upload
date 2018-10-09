package com.chengnanhuakai.upload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestJedis
 * @Description 测试短信接口的发送
 * @Author Aaryn
 * @Date 2018/9/18 15:41·
 * @Version 1.0
 */
@RestController
public class TestJedis {

    @Autowired
    StringRedisTemplate redisTemplate;


}
