package com.chengnanhuakai.upload.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @ClassName RedisMessageListenerContainerConfig
 * @Description redis过期键的监听器
 * @Author Aaryn
 * @Date 2018/9/18 18:42
 * @Version 1.0
 */
@Configuration
public class RedisMessageListenerContainerConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private KeyExpiredListener messageListener;

    @Bean
    public RedisMessageListenerContainer configRedisMessageListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        // 设置Redis的连接工厂
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        // 设置监听的Topic
        ChannelTopic channelTopic = new ChannelTopic("__keyevent@0__:expired");
        // 设置监听器
        container.addMessageListener(messageListener, channelTopic);
        return container;
    }
}
