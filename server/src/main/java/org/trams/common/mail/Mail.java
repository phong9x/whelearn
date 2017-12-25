package org.trams.common.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfig;

import org.trams.common.mail.AmazonSES;


public class Mail {
	@Autowired
    private static  VelocityConfig velocityConfig;
	
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	
	public static void main(String[] args) throws AddressException, MessagingException{
		sendMailAmazon("[Whelearn]비밀번호 찾기","Le anh ?", "kennyphong2811@gmail.com");
	 }
	
	public  static boolean sendMailAmazon(String title,String body, String recvMail) throws AddressException, MessagingException {
		try {
			//String body =  VelocityEngineUtils.mergeTemplateIntoString(velocityConfig.getVelocityEngine(), velocityid, "UTF-8", params);
			System.out.println("sendMailAmazon process");
			AmazonSES ses = new AmazonSES(title, body, recvMail);
			if(ses.sendMail()){
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String to, String subject) {
		try {
			System.setProperty("mail.mime.charset", "utf8");
			SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
			message.setTo(to);
			message.setText(String.format(simpleMailMessage.getText(), subject));
			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	public static boolean sendSimpleEmail(String toEmail,String content){
		  try {
			  if(toEmail != null){
			    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
			    	JavaMailSenderImpl sender = (JavaMailSenderImpl) context.getBean("mailSender");
			    	System.out.println(sender.getUsername() + "|" + sender.getPassword() + "|" + sender.getHost());
			    	Mail mm = (Mail) context.getBean("mail");
			        mm.sendMail(toEmail,content);
			        return true;
			  }
		} catch (Exception e) {
			System.out.println(e);
			  return false;
		}
		  return false;
	  }
}
