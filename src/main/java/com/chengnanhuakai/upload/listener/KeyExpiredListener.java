package com.chengnanhuakai.upload.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName KeyExpiredListener
 * @Description 过期键的监听
 * @Author Aaryn
 * @Date 2018/9/18 15:39
 * @Version 1.0
 */
@Component
public class KeyExpiredListener implements MessageListener {



    @Override
    public void onMessage(Message message, byte[] pattern){
        byte[] body = message.getBody();// 请使用valueSerializer
        byte[] channel = message.getChannel();
        String topic = new String(channel);     // 监听主题
        String itemValue = new String(body);    // 过期键
        try {
            // 监听过期事件选择合适的通知类型
            System.out.println("执行过期监控");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
