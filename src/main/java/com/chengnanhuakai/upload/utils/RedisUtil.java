package com.chengnanhuakai.upload.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/13 13:04
 * @Version 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;


    public void setValue(final String key,Object value){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public Object getValue(final String key){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(key);
        return o;
    }

    public void setValueWithExpire(final String key,Object value,Long time){
        setValue(key,value);
        redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

}
