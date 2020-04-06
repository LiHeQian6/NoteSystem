package com.li.project.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/admin")
    public String index(){
        int a=9/0;
        return "management";
    }
}
