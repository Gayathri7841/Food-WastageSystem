package com.example.SDP_project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;
import com.example.SDP_project.Repository.NotificationRepository;
import com.example.SDP_project.Repository.RecipientRepository;

@Service
public class RecipientServiceImpl implements RecipientService {
@Autowired
private RecipientRepository repo;
@Autowired
private NotificationRepository repo1;

@Override
public Recipient RecipientRegistration(Recipient recipient) {
	return repo.save(recipient);
}

@Override
public Recipient RecipientLoginVerification(String email, String password) {
	
 return repo.RecipientLoginVerification(email, password);
}

@Override
public List<Recipient> VerifyDuplicates(String email, String contactno) {
	return repo.VerifyDuplicates(email, contactno);
}



@Override
public Recipient findRecipientByName(String name) {
	 return repo.findByName(name);
}

@Override
public Recipient save(Recipient recipient) {
return repo.save(recipient);
	
}
public Recipient findRecipientById(int recipientId) {
    Optional<Recipient> recipient = repo.findById(recipientId);
    return recipient.orElse(null); // Return recipient if present, otherwise null
}

@Override
public List<Notification> findNotificationsByRecipientId(int recipientId) {
	return repo1.findByRecipientId(recipientId);
}

}
