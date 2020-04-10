package com.zhifou.user.advice;

import com.zhifou.entity.User;
import com.zhifou.util.Md5Encode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.ParseException;

@Component
@Aspect
public class MyAdvice{
    /**
    * @Description: 登录加密
    * @Param: 环绕通知
    * @return:
    * @Author: 景光赞
    * @Date: 2020/4/8
    */
    @Around("execution(* com.zhifou.user.service.UserService.findUserByAccountAndPassword(..))")
    public Object loginEncode(ProceedingJoinPoint joinPoint) throws ParseException {

        System.out.println("*****环绕通知*****");

        Object[] args = joinPoint.getArgs();
        Object result = null;
        System.out.println(args[1]);
        args[1] = Md5Encode.getMD5(args[1].toString().getBytes());
        System.out.println(args[1]);
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("*****环绕通知*****");
        return result;
    }
    /**
     * @Description: 注册加密
     * @Param: 环绕通知
     * @return:
     * @Author: 景光赞
     * @Date: 2020/4/8
     */
    @Around("execution(* com.zhifou.user.service.UserService.regist(..))")
    public Object registEncode(ProceedingJoinPoint joinPoint) throws ParseException {

        System.out.println("*****环绕通知*****");
        Object[] args = joinPoint.getArgs();
        User u = (User) args[0];
        Object result = null;
        System.out.println(u.getPassword());
        u.setPassword(Md5Encode.getMD5(u.getPassword().getBytes()));
        System.out.println(u.getPassword());
        args[0] = u;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("*****环绕通知*****");
        return result;
    }
    /**
     * @Author li
     * @param joinPoint
     * @return java.lang.Object
     * @Description 修改密码加密
     * @Date 22:04 2020/4/9
     **/
    @Around("execution(* com.zhifou.user.service.UserService.changePassword(..))")
    public Object changePasswordEncode(ProceedingJoinPoint joinPoint) throws ParseException {

        System.out.println("*****环绕通知*****");
        Object[] args = joinPoint.getArgs();
        String p = (String) args[1];
        Object result = null;
        System.out.println(p);
        p=Md5Encode.getMD5(p.getBytes());
        System.out.println(p);
        args[1] = p;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("*****环绕通知*****");
        return result;
    }

}
