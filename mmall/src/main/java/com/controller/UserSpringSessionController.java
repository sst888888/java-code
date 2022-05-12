package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserSpringSessionController
 * @Description
 * @Author Chaiphat
 * @Date 2020/10/19 23:39
 * @Version 1.0
 **/
@RequestMapping
@Controller
public class UserSpringSessionController {

    @ResponseBody
    @RequestMapping(value = { "/agentLottery/demo" }, name = "demo")
    public void demo(HttpSession httpSession) {

        return ;
    }

}
