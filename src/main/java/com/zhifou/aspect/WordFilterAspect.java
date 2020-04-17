package com.zhifou.aspect;

import com.zhifou.annotation.Encode;
import com.zhifou.annotation.WordFilter;
import com.zhifou.entity.User;
import com.zhifou.util.Md5Encode;
import com.zhifou.util.WordFilterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.text.ParseException;

/**
 * User: li
 * Date: 2020/4/17
 * Time: 13:31
 */
@Aspect
@Component
public class WordFilterAspect {

    @Resource
    private WordFilterUtil wordFilterUtil;


    @Pointcut("@annotation(com.zhifou.annotation.WordFilter)")
    public  void filterAspect() {}

    /**
     * @Author li
     * @param joinPoint
     * @return java.lang.Object
     * @Description 字符过滤
     * @Date 11:58 2020/4/17
     **/
    @Around("filterAspect()")
    public Object filter(ProceedingJoinPoint joinPoint) throws ParseException {
        boolean escapeHtml = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(WordFilter.class).escapeHtml();
        String description = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(WordFilter.class).description();
        System.out.println("*****Filter Start*****");
        Object[] args = joinPoint.getArgs();
        Object result = null;
        if (description.equals("updateUser")){
            args[1] = wordFilterUtil.filter(args[1].toString());
            args[3] = wordFilterUtil.filter(args[3].toString());
            if (escapeHtml)
                args[3]=HtmlUtils.htmlEscape(String.valueOf(args[3]));
        }else if(description.equals("addComment")||description.equals("updateComment")){
            args[2] = wordFilterUtil.filter(args[2].toString());
            if (escapeHtml)
                args[2]=HtmlUtils.htmlEscape(String.valueOf(args[2]));
        }
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("*****Filter End*****");
        return result;
    }
}
