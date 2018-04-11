package com.alvism.service.impl;

import com.alvism.mapper.UserMapper;
import com.alvism.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JiaMingChen on 2018/4/11.
 */
@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String index(String str) {

        System.out.println("UserMapper调用：" + userMapper.getUserById("123"));
        return "第一次使用Dubbo：" + str;
    }

}
