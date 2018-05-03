package com.alvism.service.impl;

import com.alvism.mapper.UserMapper;
import com.alvism.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Index Service 实现类
 * Created by JiaMingChen on 2018/4/11.
 */
@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private JmsTemplate jmsTemplate;


    @Resource(name = "destinationQueue")
    private Destination destination;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String index(String str) {

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("测试发送消息");
            }
        });

        System.out.println("UserMapper调用：" + userMapper.getUserById("123"));
        return "第一次使用Dubbo：" + str;
    }

}
