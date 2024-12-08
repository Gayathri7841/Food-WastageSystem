package com.example.SDP_project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="donar_table")
public class Donar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="email_id",nullable = false,unique = true,length = 200)
	private String emailid;
	@Column(name="fullname",nullable = false,length = 200)
	private String fullname;
	@Column(name="contactno",nullable = false,unique = true,length = 200)
	private String contactno;
	@Column(name="password",nullable = false,length = 200)
	private String password;
	@Column(name="otp",nullable = false,unique = true,length = 200)
	private String otp="0";
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
	    return "Donar [id=" + id + ", emailid=" + emailid + ", fullname=" + fullname + 
	           ", contactno=" + contactno + ", password=" + password + ", otp=" + otp + "]";
	}

}
