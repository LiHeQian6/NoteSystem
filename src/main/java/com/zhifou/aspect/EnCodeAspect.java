package com.zhifou.aspect;

import com.zhifou.annotation.Encode;
import com.zhifou.entity.User;
import com.zhifou.util.Md5Encode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.ParseException;

@Component
@Aspect
public class EnCodeAspect {

    @Pointcut("@annotation(com.zhifou.annotation.Encode)")
    public  void encodeAspect() {}

    /**
     * @Author li
     * @param joinPoint
     * @return java.lang.Object
     * @Description 密码加密
     * @Date 11:58 2020/4/17
     **/
    @Around("encodeAspect()")
    public Object encode(ProceedingJoinPoint joinPoint) throws ParseException {
        String description = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(Encode.class).description();
        System.out.println("*****Encode Start*****");
        Object[] args = joinPoint.getArgs();
        Object result = null;
        if (description.equals("登录")||description.equals("修改密码")){
            args[1] = Md5Encode.getMD5(args[1].toString().getBytes());
        }else if(description.equals("注册")){
            User u = (User) args[0];
            u.setPassword(Md5Encode.getMD5(u.getPassword().getBytes()));
            args[0] = u;
        }
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("*****Encode End*****");
        return result;
    }

}
