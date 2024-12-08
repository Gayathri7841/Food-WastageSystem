package com.example.SDP_project.Service;

import java.util.List;

import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;

public interface NotificationService {
	Recipient findRecipientById(int id);
	void saveNotification(Notification notification);
	
	
	void sendNotificationToAgent(Long agentId, String message);
	void createNotificationForAgent(Donations donation, Recipient recipient, String message);
	
	List<Notification> getNotificationsForRecipient(Recipient recipient);
	List<Notification> getNotificationsForRecipient(int recipientId);
	 Notification save(Notification notification);
}
