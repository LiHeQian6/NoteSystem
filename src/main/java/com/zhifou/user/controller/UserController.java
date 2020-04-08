package com.zhifou.user.controller;

import com.zhifou.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: zhifou
 * @description: login
 * @author: 景光赞
 * @create: 2020-04-07 20:53
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    
    /**
    * @Description: login
    * @Param: 
    * @return: 
    * @Author: 景光赞
    * @Date: 2020/4/7
    */
    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestParam(name = "account")String account,
                        @RequestParam(name = "password")String password, Model model){
        if(userService.findUserByAccount(account)!=null){
            if(userService.findByAccountAndPassword(account,password)!=null){
                model.addAttribute("user",userService.findByAccountAndPassword(account,password));
                return "登录成功！";
            }
            return "账号或密码错误！";
        }
        return "账号不存在，请先注册!";
    }
    /**
    * @Description: Test
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/7
    */
    @ResponseBody
    @RequestMapping("/list")
    public String findAllUser(){
        //return userService.findAll().toString();
        return userService.findByAccountAndPassword("a","b").toString()+"--"
                +userService.findById(1);
    }

}
