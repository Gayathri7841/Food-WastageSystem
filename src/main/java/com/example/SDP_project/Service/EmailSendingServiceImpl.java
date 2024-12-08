package com.example.SDP_project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailSendingServiceImpl implements EmailSendingService {
	@Autowired
    private JavaMailSender mailSender;
	public void sendEmail(String toEmail, String subject, String body) 
	{
		 SimpleMailMessage message = new SimpleMailMessage();
	      message.setFrom("boinagayathri1201@gmail.com");
	      message.setTo(toEmail);
	      message.setText(body);
	      message.setSubject(subject);
	      
	      mailSender.send(message);
		
	}
}
