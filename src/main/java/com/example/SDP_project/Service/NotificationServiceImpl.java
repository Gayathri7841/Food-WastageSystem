package com.example.SDP_project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Repository.NotificationRepository;
import com.example.SDP_project.Repository.RecipientRepository;

@Service
public class NotificationServiceImpl implements NotificationService {
	 @Autowired
	    private NotificationRepository repo;

	    @Autowired
	    private RecipientRepository repo1;

	    public Recipient findRecipientById(int id) {
	        return repo1.findById(id).orElse(null);
	    }

	    public void saveNotification(Notification notification) {
	        repo.save(notification);
	    }

		

		@Override
		public void sendNotificationToAgent(Long agentId, String message) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createNotificationForAgent(Donations donation, Recipient recipient, String message) {
			
			 Notification notification = new Notification();
			    notification.setRecipient(recipient);
			    notification.setMessage(message);
			    // You can customize this link if needed
			    repo.save(notification);
		}

		@Override
		public List<Notification> getNotificationsForRecipient(Recipient recipient) {
			return repo.findByRecipient(recipient);
		}

		@Override
		public List<Notification> getNotificationsForRecipient(int recipientId) {
			return repo.findByRecipientId(recipientId);
		}

		@Override
		public Notification save(Notification notification) {
			 return repo.save(notification);
		}

		 

		

		
	
		
}
