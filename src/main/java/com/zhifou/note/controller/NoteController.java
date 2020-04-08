package com.zhifou.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoteController {


    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到首页
     * @Date 8:54 2020/4/8
     **/
    @RequestMapping("/")
    public String toIndex(){
        return "login";
    }
}
