package com.chengnanhuakai.upload.listener;

import com.chengnanhuakai.upload.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestJedis
 * @Description 测试短信接口的发送
 * @Author Aaryn
 * @Date 2018/9/18 15:41·
 * @Version 1.0
 */
@RestController
@Api(description = "redis")
public class TestJedis {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/redis")
    @ApiOperation(value = "1")
    public void setValue(){
        String key = "testExpire";
        String value = "sss";
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,5L, TimeUnit.SECONDS);
    }
}
