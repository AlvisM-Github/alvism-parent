package com.alvism.service.impl;

import com.alvism.service.IndexService;
import org.springframework.stereotype.Service;

/**
 * Created by JiaMingChen on 2018/4/11.
 */
@Service(value = "indexService")
public class IndexServiceImpl implements IndexService {

    @Override
    public String index(String str) {
        return "第一次使用Dubbo：" + str;
    }

}
