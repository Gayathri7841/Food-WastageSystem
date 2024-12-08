package com.example.SDP_project.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipient_table")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(nullable = false, length = 200,name="name")
    private String name;

    @Column(nullable = false, length = 200,name="email")
    private String email;

    @Column(nullable = false, length = 200,name="contactno")
    private String contactno;

   

    // For example: "NGO", "Charity", "Food Bank"
    @Column(nullable=false,length=200,name="password")
    private String password;

    // Relationship to Donations
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donations> donations;
    @Column(name = "notification_message")
    private String notificationMessage;

    @Column(name = "notification_status")
    private String notificationStatus; 
    //relation between notification and recipient
    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

   

    public List<Donations> getDonations() {
        return donations;
    }

    public void setDonations(List<Donations> donations) {
        this.donations = donations;
    }

    

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	
}
