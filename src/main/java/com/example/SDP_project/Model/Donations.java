package com.example.SDP_project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Donations {
@Id
@GeneratedValue
private int id;
@Column(nullable = false,length = 200) 
private String email;
@Column(nullable = false,length = 200) 
private String fullname;
@Column(nullable = false,unique = false,length = 200)
private String contactno;
@Column(nullable = false,length = 200)
private String pickuptime;
@Column(nullable = false,length = 200)
private String pickuplocation;
@Column(nullable = false,length = 200)
private String branch;
@Column(nullable = false,length = 200)
private String donationcategory;
@Column(nullable = false,length = 200,columnDefinition = "varchar(200) default 'Pending'")
private String status="Pending";
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "recipient_id") // Foreign key to recipient table (if using Recipient entity)
private Recipient recipient;
@Column(name="assigned_agent")
private Integer assignedAgentId;
@Column(nullable = false, length = 200, columnDefinition = "varchar(200) default 'Pending'")
private String processStatus = "Pending";
public int getId() {
	return id;  
}
public void setId(int id) {
	this.id = id;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getContactno() {
	return contactno;
}
public void setContactno(String contactno) {
	this.contactno = contactno;
}
public String getPickuptime() {
	return pickuptime;
}
public void setPickuptime(String pickuptime) {
	this.pickuptime = pickuptime;
}
public String getPickuplocation() {
	return pickuplocation;
}
public void setPickuplocation(String pickuplocation) {
	this.pickuplocation = pickuplocation;
}
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getDonationcategory() {
	return donationcategory;
}
public void setDonationcategory(String donationcategory) {
	this.donationcategory = donationcategory;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getProcessStatus() {
	return processStatus;
}
public void setProcessStatus(String processStatus) {
	this.processStatus = processStatus;
}
public int getAssignedAgentId() {
	return assignedAgentId;
}
public void setAssignedAgentId(int assignedAgentId) {
	this.assignedAgentId = assignedAgentId;
}
public Recipient getRecipient() {
	return recipient;
}
public void setRecipient(Recipient recipient) {
	this.recipient = recipient;
}



}