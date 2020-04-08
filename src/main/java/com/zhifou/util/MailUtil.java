package com.zhifou.util;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    public void sendVerifyCode(String email,String verifyCode){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("知否笔记");
        message.setText("您的验证码是"+verifyCode+",有效期3分钟！");
        message.setFrom(from);
        mailSender.send(message);
    }

}
