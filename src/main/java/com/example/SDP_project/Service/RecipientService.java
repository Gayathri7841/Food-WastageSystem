package com.example.SDP_project.Service;

import java.util.List;

import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;

public interface RecipientService {
	public Recipient RecipientRegistration(Recipient recipient);
	public Recipient RecipientLoginVerification(String email,String password);
	public List<Recipient> VerifyDuplicates(String email,String contactno);
	 
	 Recipient findRecipientByName(String name) ;
	 Recipient save(Recipient recipient);
	 //Retrive recipient by id
	 Recipient findRecipientById(int recipientId);
	 
	 List<Notification> findNotificationsByRecipientId(int recipientId);
}
