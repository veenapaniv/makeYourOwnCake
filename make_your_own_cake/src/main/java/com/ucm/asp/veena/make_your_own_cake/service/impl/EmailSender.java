package com.ucm.asp.veena.make_your_own_cake.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {


		@Autowired
	    private JavaMailSender mailSender;
		
		public void sendEmailNotification(String toAdress, String subject, String emailContent) {
			 String result =null;
			 MimeMessage msg =mailSender.createMimeMessage();
			    try {
			    	
			    		MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
	                    msg.setContent(emailContent, "text/html");
	    		        helper.setTo(toAdress);
	    		        helper.setSubject(subject);
	    		        result="success";
	    		        mailSender.send(msg);
	    		    } catch (MessagingException e) {
	    		        throw new MailParseException(e);
	    		    }finally {
	    		        if(result !="success"){
	    		            result="fail";
	    		        }
	    		    }
			    }
}
