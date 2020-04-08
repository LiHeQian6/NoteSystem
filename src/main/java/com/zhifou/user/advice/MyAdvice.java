package com.zhifou.user.advice;

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
    @Around("execution(* com.zhifou.user.service.UserService.findByAccountAndPassword(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws ParseException {

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
}