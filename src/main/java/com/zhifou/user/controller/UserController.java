package com.zhifou.user.controller;

import com.zhifou.entity.User;
import com.zhifou.user.service.UserService;
import com.zhifou.util.MailUtil;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private MailUtil mailUtil;

    /**
    * @Description: login
    * @Param: 
    * @return: String
    * @Author: 景光赞
    * @Date: 2020/4/7
    */
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String toRegist(){
        return "regist";
    }

    /**
     * @Author li
     * @param account
     * @param password1
     * @param password2
     * @param vertical
     * @return java.lang.String
     * @Description 注册
     * @Date 9:26 2020/4/8
     **/
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(@RequestParam(name = "account")String account,
                         @RequestParam(name = "password1")String password1,
                         @RequestParam(name = "password2")String password2,
                         @RequestParam("vertical")String vertical){
        return "registSuccess";
    }

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到登录页
     * @Date 8:54 2020/4/8
     **/
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    /**
     * @Author 景光赞
     * @param account
     * @param password
     * @param model
     * @return java.lang.String
     * @Description
     * @Date 2020/4/7
     **/
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
     * @Author 景光赞
     * @param
     * @return java.lang.String
     * @Description 用户列表展示
     * @Date 2020/4/7
     **/
    @ResponseBody
    @RequestMapping("/list")
    public String findAllUser(){
        //return userService.findAll().toString();
        return userService.findByAccountAndPassword("a","b").toString()+"--"
                +userService.findById(1);
    }
    /**
    * @Description: Test:测试方法、接口
    * @Param:
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/7
    */
    @ResponseBody
    @RequestMapping("/list")
    public String test(){
        //return userService.findAll().toString();
//        return userService.findByAccountAndPassword("a","b").toString();
        if(userService.findUserByAccount("a")!=null){
            if(userService.findByAccountAndPassword("a","b")!=null){
                System.out.println(userService.findByAccountAndPassword("a","b"));;
                return "登录成功！";
            }
            return "账号或密码错误！";
        }
        return "账号不存在，请先注册!";
    }

    /**
     * @Author li
     * @param request
     * @return java.lang.String
     * @Description 注销登录
     * @Date 9:24 2020/4/8
     **/
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return "redirect:/";
    }

    /**
     * @Author li
     * @param email
     * @return java.lang.String
     * @Description 发送验证码
     * @Date 10:54 2020/4/8
     **/
    @RequestMapping("/getVerifyCode/{email}")
    @ResponseBody
    public String getVerifyCode(@PathVariable String email,HttpServletRequest request){
        HttpSession session = request.getSession();
        mailUtil.sendVerifyCode(email,"test");
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("verifyCode");
                System.out.println("checkCode删除成功");
                timer.cancel();
            }
        },5*60*1000);
        return "true";
    }

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到忘记密码页
     * @Date 10:54 2020/4/8
     **/
    @RequestMapping(value = "/forgetPassword",method = RequestMethod.GET)
    public String toForgetPassword(){
        return "forget_password";
    }
    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到修改密码页
     * @Date 10:54 2020/4/8
     **/
    @RequestMapping(value = "/changePassword",method = RequestMethod.GET)
    public String toChangePassword(){
        return "change_password";
    }
    /**
     * @Author li
     * @param email
     * @param password1
     * @param password2
     * @return java.lang.String
     * @Description 修改密码
     * @Date 10:55 2020/4/8
     **/
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public String changePassword(@RequestParam("email")String email,
                                 @RequestParam("password1")String password1,
                                 @RequestParam("password2")String password2){
        return "true";
    }

}
