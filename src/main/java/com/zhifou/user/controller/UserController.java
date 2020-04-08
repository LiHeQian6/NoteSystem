package com.zhifou.user.controller;

import com.zhifou.entity.User;
import com.zhifou.user.service.UserService;
import com.zhifou.util.MailUtil;
import com.zhifou.util.VerifyUtil;
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
    private VerifyUtil verifyUtil;

    @Resource
    private MailUtil mailUtil;

    /**
     * @Author li
     * @param
     * @return java.lang.String
     * @Description 跳转到注册页
     * @Date 8:56 2020/4/8
     **/
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
    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(@RequestParam(name = "account")String account,
                         @RequestParam(name = "password1")String password1,
                         @RequestParam(name = "password2")String password2,
                         @RequestParam("vertical")String vertical,HttpServletRequest request){
        HttpSession session = request.getSession();
        String orignVerify = (String) session.getAttribute("verifyCode");
        if(userService.findUserByAccount(account)==null){
            if(password1.equals(password2)){
                if(vertical.equals(orignVerify)){
                    userService.regist(new User(account,password1));
                    return "注册成功！";
                }
                return "验证码错误或已失效";
            }
            return "密码输入不一致";
        }
        return "账号已注册！";
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
     * @param request
     * @return java.lang.String
     * @Description
     * @Date 2020/4/7
     **/
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam(name = "account")String account,
                        @RequestParam(name = "password")String password, HttpServletRequest request){
        if(userService.findUserByAccount(account)!=null){
            User user = userService.findByAccountAndPassword(account, password);
            if(user !=null){
                request.getSession().setAttribute("user", user);
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
        String verifyCode = verifyUtil.getVerify();
        session.setAttribute("verifyCode",verifyCode);
        mailUtil.sendVerifyCode(email,verifyCode);
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute("verifyCode");
                timer.cancel();
            }
        },3*60*1000);
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
    @ResponseBody
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public String changePassword(@RequestParam("email")String email,
                                 @RequestParam("password1")String password1,
                                 @RequestParam("password2")String password2){

        return "true";
    }

}
