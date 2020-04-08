package com.zhifou.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Description: <br />
 * Project: itclass <br />
 * ClassName: MailUtil <br />
 * Copyright: Copyright (c) 2011 onest<br />
 * 
 * @author wangwei
 * @version 1.0 2013-5-6下午3:25:31
 * 
 */
public class MailUtil {

	/**
	 * 
	 * @desc 发送邮件，用于找回密码。
	 * @author wangwei
	 * @createDate 2013-5-9
	 * @param toMail
	 */
	public static void sendMail4getPassword(String url,String toMail) throws Exception{
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost("smtp.163.com");
		// 建立邮件消息
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage,"utf-8");
		try {
			messageHelper.setTo(toMail);
			messageHelper.setFrom("itclass@163.com");
			messageHelper.setSubject("XXX重置密码");
			String content = "";
			messageHelper.setText(content,true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		senderImpl.setUsername("onest_itclass");
		senderImpl.setPassword("xxx");

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);
	}
}
