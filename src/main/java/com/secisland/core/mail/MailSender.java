/*
 *	Copyright © 2015 Nanjing Secisland Network Technology Co., Ltd. All rights reserved.
 *	南京赛克蓝德网络科技有限公司   版权所有
 *	http://www.secisland.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.secisland.core.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSender {

	private JavaMailSenderImpl mailSender;

    public void send(MailMessage mail) throws MessagingException{
    	if ( mailSender == null ){
    		return;
    	}
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        if ( mail.getEncoded() == null ){
        	messageHelper = new MimeMessageHelper(message,true,"UTF-8");
        } else {
        	messageHelper = new MimeMessageHelper(message,true,mail.getEncoded());
        }
		
		if (mail.getFileList()!=null){
			for ( String filePath : mail.getFileList()){
				File resource = new File(filePath);
                if(resource.exists()){
                    messageHelper.addAttachment(resource.getName(),resource);
                }
			}
		}
        messageHelper.setFrom(mailSender.getUsername());
        if ( mail.getTo()!= null ){
        	String[] strAddresses = mail.getTo().split(",");
        	messageHelper.setTo(strAddresses);
        }
        
        if ( mail.getCc()!= null ){
        	String[] strAddresses = mail.getCc().split(",");
        	messageHelper.setCc(strAddresses);
        }
        messageHelper.setSubject(mail.getSubject());
        if ( mail.isHtml() ){
        	messageHelper.setText("text/html;charset=utf-8", mail.getText());
        } else {
        	messageHelper.setText(mail.getText());
        }
        mailSender.send(message);
    }
    
    public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void setMailSender(MailConfig mailConfig) {
		if ( mailConfig == null ){
			return ;
		}
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailConfig.getHost());
		mailSender.setPort(mailConfig.getPort());
		mailSender.setUsername(mailConfig.getUsername());
		mailSender.setPassword(mailConfig.getPassword());
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", mailConfig.getAuth());
		properties.setProperty("mail.smtp.timeout", mailConfig.getTimeout());
		properties.setProperty("mail.smtp.socketFactory.class",mailConfig.getSocketFactory());
		mailSender.setJavaMailProperties(properties);

		this.mailSender = mailSender;
	}
}
