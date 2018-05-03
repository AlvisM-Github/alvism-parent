package com.alvism.controller;

import com.alvism.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Index 控制器
 * Created by JiaMingChen on 2018/4/11.
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("welcome")
    public ModelAndView welcome(){
        System.out.println(indexService.index("陈嘉明"));
        ModelAndView mav = new ModelAndView();
        mav.addObject("str", indexService.index("陈嘉明"));
        return mav;
    }

}
