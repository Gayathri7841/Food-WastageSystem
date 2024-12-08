package com.example.SDP_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SDP_project.Model.Donations;
import com.example.SDP_project.Model.Notification;
import com.example.SDP_project.Model.Recipient;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>  {

	
	List<Notification> findByRecipient(Recipient recipient);
	
	@Query("SELECT n FROM Notification n WHERE n.recipient.id = :recipientId")
	List<Notification> findByRecipientId(@Param("recipientId") int recipientId);
}
