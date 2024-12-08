package com.example.SDP_project.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_table")
public class Notification {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	 @Column(nullable = false, length = 200)
	    private String message;

	    @Column(nullable = false)
	    private LocalDateTime timestamp;

	    @ManyToOne
	    @JoinColumn(name = "recipient_id", nullable = false)
	    private Recipient recipient;
	    @PrePersist
	    public void prePersist() {
	        if (this.timestamp == null) {
	            this.timestamp = LocalDateTime.now();  // Set the timestamp to current time if not already set
	        }
	    }
	    @Column(name = "notification_message")
	    private String notificationMessage;
	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(LocalDateTime timestamp) {
	        this.timestamp = timestamp;
	    }

	    public Recipient getRecipient() {
	        return recipient;
	    }

	    public void setRecipient(Recipient recipient) {
	        this.recipient = recipient;
	    }

		public String getNotificationMessage() {
			return notificationMessage;
		}

		public void setNotificationMessage(String notificationMessage) {
			this.notificationMessage = notificationMessage;
		}
	}
