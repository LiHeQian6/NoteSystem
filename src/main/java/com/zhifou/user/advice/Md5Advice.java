package com.zhifou.user.advice;

import com.zhifou.util.Md5Encode;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
@Aspect
public class Md5Advice implements MethodBeforeAdvice {
    @Override
//    @Before("execution(* com.example.zhifou.user.service.UserService(..))")
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("加密前密码："+objects[1]);
        String newPwd = Md5Encode.getMD5(objects[1].toString().getBytes());
        objects[1] = newPwd;
    }
}
