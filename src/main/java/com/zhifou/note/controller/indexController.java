package com.zhifou.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("regist")
    public String index(){
        return "regist";
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
